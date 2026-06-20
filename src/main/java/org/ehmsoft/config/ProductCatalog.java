package org.ehmsoft.config;

import java.util.List;
import java.util.Map;

public class ProductCatalog {
    public static final Map<String, List<String>> PRODUCTS =
            Map.of(
                    "notebook", List.of(
                            "Apple MacBook Air M2",
                            "Dell XPS 13",
                            "Lenovo ThinkPad X1 Carbon"
                    ),

                    "smartphone", List.of(
                            "iPhone 14",
                            "Samsung Galaxy S23",
                            "Google Pixel 7"
                    ),

                    "auriculares", List.of(
                            "Sony WH-1000XM4",
                            "Bose QuietComfort 45",
                            "Anker Soundcore Life Q30"
                    )
            );
}
