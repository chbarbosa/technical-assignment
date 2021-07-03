package com.finder.servingwebcontent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Executed after context initiation.
 * @author chbarbosa
 *
 */
@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    private static final String STORES_JSON = "stores.json";

	private static final Logger LOG =
      LoggerFactory.getLogger(CommandLineAppStartupRunner.class);

    /**
     * A {@link StoreService} instance.
     */
    @Autowired
    private StoreService storeService = null;


    @Override
    public void run(String...args) throws Exception {
        LOG.info("App initialized");
        // Read the file data.
        String jsonStr = FileHelper.readString(STORES_JSON);
        // Convert the json string.
        this.storeService.convertJson(jsonStr);
    }
}
