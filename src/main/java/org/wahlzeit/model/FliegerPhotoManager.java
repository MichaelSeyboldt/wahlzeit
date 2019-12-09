package org.wahlzeit.model;

import java.util.logging.Logger;

public class FliegerPhotoManager extends PhotoManager {

    protected static final FliegerPhotoManager instance = new FliegerPhotoManager();

    private static final Logger log = Logger.getLogger(FliegerPhotoManager.class.getName());

    public FliegerPhotoManager() {
       photoTagCollector =  FliegerPhotoFactory.getInstance().createPhotoTagCollector();
    }


    public static FliegerPhotoManager getInstance(){
        return instance;
    }



    @Override
    public Photo getPhoto(PhotoId id) throws IllegalArgumentException {
        return instance.getPhotoFromId(id);
    }

    @Override
    public Photo getPhotoFromId(PhotoId id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("Id may not be null");
        }

        Photo result = doGetPhotoFromId(id);

        if (result == null) {
            result = FliegerPhotoFactory.getInstance().loadPhoto(id);
            if (result != null) {
                doAddPhoto(result);
            } else {
                log.info("cloould not load photo id:" +id.stringValue);
                //null seems to be the corrected value
            }
        }

        return result;
    }
}
