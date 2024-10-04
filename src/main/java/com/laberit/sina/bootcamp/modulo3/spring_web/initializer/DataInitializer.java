package com.laberit.sina.bootcamp.modulo3.spring_web.initializer;

import com.laberit.sina.bootcamp.modulo3.spring_web.enumeration.Category;
import com.laberit.sina.bootcamp.modulo3.spring_web.enumeration.Role;
import com.laberit.sina.bootcamp.modulo3.spring_web.model.Product;
import com.laberit.sina.bootcamp.modulo3.spring_web.model.ProductDetail;
import com.laberit.sina.bootcamp.modulo3.spring_web.model.User;
import com.laberit.sina.bootcamp.modulo3.spring_web.repository.ProductRepository;
import com.laberit.sina.bootcamp.modulo3.spring_web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        initializeProducts();
        initializeUsers();
    }

    private void initializeProducts() {
        List<Product> products = List.of(
                new Product("GPU001", 289.99, 10L, Category.GRAPHIC_CARD, List.of(
                        new ProductDetail("Tarjeta Gráfica MSI GeForce RTX 4060", "VENTUS 2X BLACK OC 8GB GDDR6 DLSS3", "es", null),
                        new ProductDetail("Graphic Card MSI GeForce RTX 4060", "VENTUS 2X BLACK OC 8GB GDDR6 DLSS3", "en", null)
                )),
                new Product("GPU002", 1079.99, 20L, Category.GRAPHIC_CARD, List.of(
                        new ProductDetail("Tarjeta Gráfica MSI GeForce RTX 4080", "VENTUS 3X BLACK OC 12GB GDDR6 DLSS3", "es", null),
                        new ProductDetail("Graphic Card MSI GeForce RTX 4080", "VENTUS 3X BLACK OC 12GB GDDR6 DLSS3", "en", null)
                )),
                new Product("GPU003", 387.99, 30L, Category.GRAPHIC_CARD, List.of(
                        new ProductDetail("Tarjeta Gráfica MSI GeForce RTX 4060 Ti", "VENTUS 3X E OC 8GB GDDR6 DLSS3", "es", null),
                        new ProductDetail("Graphic Card MSI GeForce RTX 4060 Ti", "VENTUS 3X E OC 8GB GDDR6 DLSS3", "en", null)
                )),
                new Product("CPU001", 172.70, 40L, Category.PROCESSOR, List.of(
                        new ProductDetail("Procesador AMD Ryzen 7 5800X", "8 Núcleos, 16 Hilos, 3.8 GHz hasta 4.7 GHz", "es", null),
                        new ProductDetail("Processor AMD Ryzen 7 5800X", "8 Cores, 16 Threads, 3.8 GHz up to 4.7 GHz", "en", null)
                )),
                new Product("CPU002", 289.99, 50L, Category.PROCESSOR, List.of(
                        new ProductDetail("Procesador AMD Ryzen 9 5900X", "12 Núcleos, 24 Hilos, 3.7 GHz hasta 4.8 GHz", "es", null),
                        new ProductDetail("Processor AMD Ryzen 9 5900X", "12 Cores, 24 Threads, 3.7 GHz up to 4.8 GHz", "en", null)
                )),
                new Product("CPU003", 499.99, 60L, Category.PROCESSOR, List.of(
                        new ProductDetail("Procesador AMD Ryzen 9 5950X", "16 Núcleos, 32 Hilos, 3.4 GHz hasta 4.9 GHz", "es", null),
                        new ProductDetail("Processor AMD Ryzen 9 5950X", "16 Cores, 32 Threads, 3.4 GHz up to 4.9 GHz", "en", null)
                )),
                new Product("RAM001", 59.99, 70L, Category.RAM, List.of(
                        new ProductDetail("Memoria RAM Corsair Vengeance LPX", "16 GB (2 x 8 GB) DDR4 3200 MHz CL16", "es", null),
                        new ProductDetail("RAM Memory Corsair Vengeance LPX", "16 GB (2 x 8 GB) DDR4 3200 MHz CL16", "en", null)
                )),
                new Product("RAM002", 109.99, 80L, Category.RAM, List.of(
                        new ProductDetail("Memoria RAM Corsair Vengeance RGB PRO", "32 GB (2 x 16 GB) DDR4 3200 MHz CL16", "es", null),
                        new ProductDetail("RAM Memory Corsair Vengeance RGB PRO", "32 GB (2 x 16 GB) DDR4 3200 MHz CL16", "en", null)
                )),
                new Product("RAM003", 199.99, 90L, Category.RAM, List.of(
                        new ProductDetail("Memoria RAM Corsair Vengeance RGB PRO", "64 GB (2 x 32 GB) DDR4 3200 MHz CL16", "es", null),
                        new ProductDetail("RAM Memory Corsair Vengeance RGB PRO", "64 GB (2 x 32 GB) DDR4 3200 MHz CL16", "en", null)
                )),
                new Product("SSD001", 59.99, 100L, Category.STORAGE, List.of(
                        new ProductDetail("Disco Duro SSD Kingston A2000", "500 GB NVMe PCIe Gen 3.0 x4", "es", null),
                        new ProductDetail("SSD Kingston A2000", "500 GB NVMe PCIe Gen 3.0 x4", "en", null)
                )),
                new Product("SSD002", 109.99, 110L, Category.STORAGE, List.of(
                        new ProductDetail("Disco Duro SSD Kingston A2000", "1 TB NVMe PCIe Gen 3.0 x4", "es", null),
                        new ProductDetail("SSD Kingston A2000", "1 TB NVMe PCIe Gen 3.0 x4", "en", null)
                )),
                new Product("HDD001", 199.99, 120L, Category.STORAGE, List.of(
                        new ProductDetail("Disco Duro Seagate Barracuda", "4 TB 3.5\" SATA 6 Gb/s 5400 RPM", "es", null),
                        new ProductDetail("HDD Seagate Barracuda", "4 TB 3.5\" SATA 6 Gb/s 5400 RPM", "en", null)
                )),
                new Product("CASE001", 59.99, 130L, Category.CASE, List.of(
                        new ProductDetail("Caja de Ordenador NZXT H510", "ATX, USB 3.1, Panel Lateral de Cristal Templado", "es", null),
                        new ProductDetail("Computer Case NZXT H510", "ATX, USB 3.1, Tempered Glass Side Panel", "en", null)
                )),
                new Product("CASE002", 109.99, 140L, Category.CASE, List.of(
                        new ProductDetail("Caja de Ordenador NZXT H710", "ATX, USB 3.1, Panel Lateral de Cristal Templado", "es", null),
                        new ProductDetail("Computer Case NZXT H710", "ATX, USB 3.1, Tempered Glass Side Panel", "en", null)
                )),
                new Product("COOLER001", 199.99, 150L, Category.COOLER, List.of(
                        new ProductDetail("Refrigeración Líquida Corsair H100i RGB PLATINUM", "240 mm, RGB, 2 Ventiladores PWM", "es", null),
                        new ProductDetail("Corsair H100i RGB PLATINUM Liquid Cooling", "240 mm, RGB, 2 PWM Fans", "en", null)
                )),
                new Product("COOLER002", 59.99, 160L, Category.COOLER, List.of(
                        new ProductDetail("Refrigeración Líquida Corsair H60", "120 mm, 1 Ventilador PWM", "es", null),
                        new ProductDetail("Corsair H60 Liquid Cooling", "120 mm, 1 PWM Fan", "en", null)
                )),
                new Product("PSU001", 109.99, 170L, Category.POWER_SUPPLY, List.of(
                        new ProductDetail("Fuente de Alimentación Corsair CV550", "550 W, 80 PLUS Bronze", "es", null),
                        new ProductDetail("Corsair CV550 Power Supply", "550 W, 80 PLUS Bronze", "en", null)
                )),
                new Product("PSU002", 199.99, 180L, Category.POWER_SUPPLY, List.of(
                        new ProductDetail("Fuente de Alimentación Corsair RM750x", "750 W, 80 PLUS Gold", "es", null),
                        new ProductDetail("Corsair RM750x Power Supply", "750 W, 80 PLUS Gold", "en", null)
                )),
                new Product("MTB001", 59.99, 190L, Category.MOTHERBOARD, List.of(
                        new ProductDetail("Placa Base MSI B450 TOMAHAWK MAX", "ATX, Socket AM4, DDR4", "es", null),
                        new ProductDetail("MSI B450 TOMAHAWK MAX Motherboard", "ATX, Socket AM4, DDR4", "en", null)
                )),
                new Product("MTB002", 109.99, 200L, Category.MOTHERBOARD, List.of(
                        new ProductDetail("Placa Base MSI B550 TOMAHAWK", "ATX, Socket AM4, DDR4", "es", null),
                        new ProductDetail("MSI B550 TOMAHAWK Motherboard", "ATX, Socket AM4, DDR4", "en", null)
                )),
                new Product("MTB003", 199.99, 210L, Category.MOTHERBOARD, List.of(
                        new ProductDetail("Placa Base MSI X570 TOMAHAWK", "ATX, Socket AM4, DDR4", "es", null),
                        new ProductDetail("MSI X570 TOMAHAWK Motherboard", "ATX, Socket AM4, DDR4", "en", null)
                )),
                new Product("OTH001", 59.99, 220L, Category.OTHER, List.of(
                        new ProductDetail("Adaptador WiFi TP-Link Archer T2U Nano", "AC600, USB 2.0, 2.4 GHz / 5 GHz", "es", null),
                        new ProductDetail("TP-Link Archer T2U Nano WiFi Adapter", "AC600, USB 2.0, 2.4 GHz / 5 GHz", "en", null)
                )),
                new Product("OTH002", 109.99, 230L, Category.OTHER, List.of(
                        new ProductDetail("Adaptador WiFi TP-Link Archer T4U", "AC1300, USB 3.0, 2.4 GHz / 5 GHz", "es", null),
                        new ProductDetail("TP-Link Archer T4U WiFi Adapter", "AC1300, USB 3.0, 2.4 GHz / 5 GHz", "en", null)
                )),
                new Product("OTH003", 199.99, 240L, Category.OTHER, List.of(
                        new ProductDetail("Adaptador WiFi TP-Link Archer T9UH", "AC1900, USB 3.0, 2.4 GHz / 5 GHz", "es", null),
                        new ProductDetail("TP-Link Archer T9UH WiFi Adapter", "AC1900, USB 3.0, 2.4 GHz / 5 GHz", "en", null)
                ))
        );

        products.forEach(product -> product.getProductDetail().forEach(detail -> detail.setProduct(product)));
        productRepository.saveAll(products);
    }

    private void initializeUsers() {
        User adminUser = new User();
        adminUser.setName("Admin");
        adminUser.setEmail("admin@laberit.com");
        adminUser.setPassword(passwordEncoder.encode("America@@2023"));
        adminUser.setRole(Role.ADMIN);
        userRepository.save(adminUser);

        User clientUser = new User();
        clientUser.setName("Client");
        clientUser.setEmail("client@laberit.com");
        clientUser.setPassword(passwordEncoder.encode("America@@2023"));
        clientUser.setRole(Role.CLIENT);
        userRepository.save(clientUser);
    }
}