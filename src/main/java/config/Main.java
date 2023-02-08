package config;

import dao.*;
import entity.address.Address;
import entity.address.City;
import entity.address.Country;
import entity.customer.*;
import entity.film.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class Main {
    private final SessionFactory sessionFactory;

    private final ActorDAO actorDAO;
    private final AddressDAO addressDAO;
    private final CategoryDAO categoryDAO;
    private final CityDAO cityDAO;
    private final CountryDAO countryDAO;
    private final CustomerDAO customerDAO;
    private final Film_TextDAO film_textDAO;
    private final FilmDAO filmDAO;
    private final InventoryDAO inventoryDAO;
    private final LanguageDAO languageDAO;
    private final PaymentDAO paymentDAO;
    private final RentalDAO rentalDAO;
    private final StaffDAO staffDAO;
    private final StoreDAO storeDAO;

    private Main() {
        Properties properties = new Properties();
        properties.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
        properties.put(Environment.URL, "jdbc:mysql://localhost:3306/module_hibernate_2");
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.connection.useUnicode", true);
        properties.put("hibernate.connection.characterEncoding", "UTF-8");
        properties.put("hibernate.connection.charSet", "UTF-8");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "22022008");
        properties.setProperty("hibernate.current_session_context_class", "thread");

        sessionFactory = new Configuration()
                .setProperties(properties)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Inventory.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Rental.class)
                .addAnnotatedClass(Staff.class)
                .addAnnotatedClass(Store.class)
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(Film.class)
                .addAnnotatedClass(Film_Text.class)
                .addAnnotatedClass(Language.class)
                .addAnnotatedClass(Rating.class)
                .buildSessionFactory();
        actorDAO = new ActorDAO(sessionFactory);
        addressDAO = new AddressDAO(sessionFactory);
        categoryDAO = new CategoryDAO(sessionFactory);
        cityDAO = new CityDAO(sessionFactory);
        countryDAO = new CountryDAO(sessionFactory);
        customerDAO = new CustomerDAO(sessionFactory);
        film_textDAO = new Film_TextDAO(sessionFactory);
        filmDAO = new FilmDAO(sessionFactory);
        inventoryDAO = new InventoryDAO(sessionFactory);
        languageDAO = new LanguageDAO(sessionFactory);
        paymentDAO = new PaymentDAO(sessionFactory);
        rentalDAO = new RentalDAO(sessionFactory);
        staffDAO = new StaffDAO(sessionFactory);
        storeDAO = new StoreDAO(sessionFactory);

    }

    public static void main(String[] args) {
        Main main = new Main();
        Customer customer = main.createCustomer();
        main.customerReturnInventoryToStore();
        main.customerRentInventory(customer);
        main.newFilmWasMade();
    }

    private void newFilmWasMade() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Language language = languageDAO.getItems(0,20).stream().unordered().findAny().get();
            List<Category> categories = categoryDAO.getItems(0,5);
            List<Actor> actors = actorDAO.getItems(0,10);

            Film film = new Film();
            film.setActorSet(new HashSet<>(actors));
            film.setRating(Rating.NC17);
            film.setSpecial_features(Set.of(Feature.TRAILERS,Feature.COMMENTARIES));
            film.setLength(123);
            film.setReplacement_cost(10.0);
            film.setRental_rate(2.2);
            film.setLanguage_id(language);
            film.setDescription("new comedy film");
            film.setTitle("my move title");
            film.setRental_duration(33);
            film.setOriginal_language_id(language);
            film.setCategorySet(new HashSet<>(categories));
            film.setRelease_year(Year.now());
            filmDAO.save(film);

            Film_Text film_text = new Film_Text();
            film_text.setId(film.getId());
            film_text.setFilm_id(film);
            film_text.setDescription("my Description Film text");
            film_text.setTitle("my title Film text");
            film_textDAO.save(film_text);

            session.getTransaction().commit();
        }
    }

    private void customerRentInventory(Customer customer) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Film film = filmDAO.getFirstAvailableFilmForRent();
            Store store = storeDAO.getItems(0,1).get(0);

            Inventory inventory = new Inventory();
            inventory.setFilm_id(film);
            inventory.setStore_id(store);

            inventoryDAO.save(inventory);

            Staff staff = store.getManager_staff_id();

            Rental rental = new Rental();
            rental.setRental_date(LocalDateTime.now());
            rental.setCustomer_id(customer);
            rental.setInventory_id(inventory);
            rental.setStaff_id(staff);

            rentalDAO.save(rental);

            Payment payment = new Payment();
            payment.setRental_id(rental);
            payment.setPayment_date(LocalDateTime.now());
            payment.setCustomer_id(customer);
            payment.setAmount(55.44);
            payment.setStaff_id(staff);

            paymentDAO.save(payment);

            session.getTransaction().commit();
        }
    }

    private void customerReturnInventoryToStore(){
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Rental rental = rentalDAO.getAnyUnreturnedRental();
            rental.setReturn_date(LocalDateTime.now());

            rentalDAO.save(rental);


            session.getTransaction().commit();
        }
    }

    private Customer createCustomer(){
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Store store = storeDAO.getItems(0,1).get(0);

            City city = cityDAO.getByName("Akishima");

            Address address = new Address();
            address.setAddress("address");
            address.setCity_id(city);
            address.setDistrict("district");
            address.setPhone("+380967743322");
            addressDAO.save(address);

            Customer customer = new Customer();
            customer.setActive(true);
            customer.setEmail("gmail.com");
            customer.setLast_name("Orlov");
            customer.setFirst_name("Dmitry");
            customer.setStore_id(store);
            customer.setAddress_id(address);
            customerDAO.save(customer);

            session.getTransaction().commit();
            return customer;

        }
    }
}
