import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;


public class FrameworkSystemAPI {

    public void create(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnitName");

        // Criação da entidade a ser persistida no Postgres
        Funcionario frameworker = new Funcionario();
        frameworker.setName("Guilherme Melo");
        frameworker.setSalario(2200);

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Transação
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(frameworker);
            entityManager.getTransaction().commit();
            entityManagerFactory.close();
        }catch(HibernateException hibernateEx){
            try{
                entityManager.getTransaction().rollback();
            }catch(RuntimeException runtimeEx){
                System.err.printf("Não foi possível realizar rollback da transação", runtimeEx);
            }
            hibernateEx.printStackTrace();
        }finally {
            if(entityManager!= null) {
                entityManager.close();
            }
        }

    }

    public Funcionario read(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnitName");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        int id = 1;
        //String nome = "Guilherme";
        Funcionario frameworker = new Funcionario();

        try{
            frameworker = entityManager.find(Funcionario.class, id);

            System.out.println(frameworker.getId() + " " + frameworker.getName() + " " + frameworker.getSalario());
        }catch(HibernateException hibernateEx){
            hibernateEx.printStackTrace();
        }

        // frameworker pode ser manipulado a partir de um retorno do metodo read()

        return frameworker;
    }

    public void update(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnitName");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Funcionario frameworker = new Funcionario();

        frameworker.setId(2);

        try{
            frameworker = entityManager.find(Funcionario.class, frameworker.getId());
            frameworker.setName("Guilherme Melo");

            entityManager.getTransaction().commit();
            entityManager.close();
            entityManagerFactory.close();
        }catch(HibernateException hibernateEx){
            hibernateEx.printStackTrace();
        }

    }

    public void delete() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnitName");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        int id = 2;

        try {
            Funcionario frameworker = entityManager.find(Funcionario.class, id);
            entityManager.getTransaction().begin();
            entityManager.remove(frameworker);
            entityManager.getTransaction().commit();
            entityManagerFactory.close();
        }catch(HibernateException hibernateEx){
            hibernateEx.printStackTrace();
        }
    }

}
