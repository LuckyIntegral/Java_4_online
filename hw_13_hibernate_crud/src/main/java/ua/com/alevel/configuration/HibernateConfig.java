package ua.com.alevel.configuration;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfig {
    private static HibernateConfig instance;
    private final SessionFactory factory;

    private HibernateConfig() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        factory = configuration.buildSessionFactory();
    }

    public static HibernateConfig getInstance() {
        if (instance == null) {
            instance = new HibernateConfig();
        }
        return instance;
    }

    public SessionFactory getFactory() {
        return factory;
    }
}
