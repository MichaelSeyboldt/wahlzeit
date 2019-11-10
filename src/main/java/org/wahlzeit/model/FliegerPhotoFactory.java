package org.wahlzeit.model;

import org.wahlzeit.services.LogBuilder;

import java.util.logging.Logger;

public class FliegerPhotoFactory extends PhotoFactory {
    private static final Logger log = Logger.getLogger(FliegerPhotoFactory.class.getName());

    private static FliegerPhotoFactory instance = null;

    protected FliegerPhotoFactory(){}

    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    public static void initialize() {
        getInstance(); // drops result due to getInstance() side-effects
    }

    /**
     * Public singleton access method.
     */
    public static synchronized PhotoFactory getInstance() {
        if (instance == null) {
            log.config(LogBuilder.createSystemMessage().addAction("setting FliegerPhotoFactory").toString());
            setInstance(new PhotoFactory());
        }

        return instance;
    }

    /**
     * Method to set the singleton instance of PhotoFactory.
     */
    protected static synchronized void setInstance(FliegerPhotoFactory photoFactory) {
        if (instance != null) {
            throw new IllegalStateException("attempt to initalize PhotoFactory twice");
        }

        instance = photoFactory;
    }

    /**
     * @methodtype factory
     */
    public FliegerPhoto createPhoto() {
        return new FliegerPhoto();
    }

    /**
     * Creates a new photo with the specified id
     */
    public FliegerPhoto createPhoto(PhotoId id) {
        return new FliegerPhoto(id);
    }

}
