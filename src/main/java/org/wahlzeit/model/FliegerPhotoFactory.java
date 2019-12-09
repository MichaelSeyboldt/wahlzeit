package org.wahlzeit.model;

import org.wahlzeit.services.LogBuilder;

import java.util.logging.Logger;

public class FliegerPhotoFactory extends PhotoFactory {
    private static final Logger log = Logger.getLogger(FliegerPhotoFactory.class.getName());


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
            setInstance(new FliegerPhotoFactory());
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
    public FliegerPhoto createPhoto() throws IllegalStateException {
        FliegerPhoto photo = null;
        try {
            photo =new FliegerPhoto();
        } catch (Exception e) {
            log.warning("Could not genertate Photo: "+ e.toString());
            //escalate
            throw new IllegalStateException("Photo could not be generated. In: " + this.getClass());
        }
        return photo;
    }

    /**
     * Creates a new photo with the specified id
     */
    public FliegerPhoto createPhoto(PhotoId id) throws IllegalStateException {
        FliegerPhoto photo;
        try {
            photo = new FliegerPhoto(id);
        } catch (Exception e) {
            log.warning("Could not genertate Photo: "+ e.toString());
            //escalate
            throw new IllegalStateException("Photo could not be generated. In: " + this.getClass());
        }

        return photo;
    }

}
