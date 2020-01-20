package org.wahlzeit.model;

import org.wahlzeit.services.*;

import java.util.*;



public class FliegerManager
        //extends ObjectManager
        {

    protected FliegerType rootType;

    protected static FliegerManager instance;

    protected Set<FliegerType> typeSet;


    protected void assertNotNullArg(Object object,String msg){
        if(object==null){
            throw new IllegalArgumentException("is null: " + msg);
        }
    }
    protected void assertUsableArg(float arg, String msg ){
        if(arg <= 0){
            throw new IllegalArgumentException("arg must be positive: " + msg);
        }
    }

    protected void assertUsableArg(int arg, String msg ){
        if(arg <= 0){
            throw new IllegalArgumentException("arg must be positive: " + msg);
        }
    }

    protected FliegerManager(){
        super();
        rootType = new FliegerType("Flieger", "Flieger");

        typeSet  = new HashSet<FliegerType>();
        typeSet.add(rootType);

    }

    protected static synchronized FliegerManager getInstance(){
        if(instance==null){
            instance = new FliegerManager();
        }
        return instance;
    }

    public Iterator<FliegerType> getAllTypes() {
        return typeSet.iterator();
    }

    public void addType(FliegerType superType, FliegerType type) throws IllegalArgumentException {
        assertNotNullArg(superType, "superType");
        if(!typeSet.contains(superType)) throw  new IllegalArgumentException("invalid superType");
        assertNotNullArg(type, "type");

    }
    protected void doAddType(FliegerType superType, FliegerType type){
        typeSet.add(type);
        superType.addSubType(type);
    }

    public void initDefault(){
        FliegerType segelfieger = new FliegerType(rootType,"Segelflieger", "Segelflieger");
        doAddType(rootType,segelfieger);
        FliegerType pzl = new FliegerType(segelfieger,"PZL", "PZL", "PZL");
        doAddType(segelfieger, pzl);
        FliegerType shempp = new FliegerType(segelfieger, "Schempp-Hirth", "Schempp-Hirth","Schempp-Hirth");
        doAddType(segelfieger, shempp);
        FliegerType pirat = new FliegerType(pzl, "SZD-30","PZL","SZD-30 Pirat", 15, false, 1);
        doAddType(pzl,pirat);

    }

    public boolean isSubType(FliegerType type, FliegerType superType) throws IllegalArgumentException{
        assertNotNullArg(type, "type");
        assertNotNullArg(superType , "superType");
        if(!typeSet.contains(superType)) throw  new IllegalArgumentException("invalid superType");
        if(!typeSet.contains(type)) throw  new IllegalArgumentException("invalid type");

        return doIsSubtype(type,superType);

    }

    protected boolean doIsSubtype(FliegerType type ,FliegerType superType){
        if(type.equals(superType)) return true;
        FliegerType mem = type.getSuperType();
        for(;mem != null; mem =mem.getSuperType()){
            if(mem.equals(superType)) return true;
        }

        return false;
    }

    public FliegerType getRootType() {
        return rootType;
    }

}
