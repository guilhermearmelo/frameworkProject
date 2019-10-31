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
        entityManager.getTransaction().begin();
        entityManager.persist(frameworker);
        entityManager.getTransaction().commit();
        entityManagerFactory.close();
    }

    public void read(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnitName");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        int id = 1;
        //String nome = "Guilherme";

        Funcionario frameworker = entityManager.find(Funcionario.class, id);

        System.out.println(frameworker.getId() + " " + frameworker.getName() + " " + frameworker.getSalario());

        // frameworker pode ser manipulado a partir de um retorno do metodo read()
    }

    public void update(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnitName");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Funcionario frameworker = new Funcionario();

        frameworker.setId(1);

        frameworker = entityManager.find(Funcionario.class, frameworker.getId());
        frameworker.setName("José da Silva");

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();

    }

    public void delete() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnitName");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        int id = 1;

        Funcionario frameworker = entityManager.find(Funcionario.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(frameworker);
        entityManager.getTransaction().commit();
        entityManagerFactory.close();
    }

}
