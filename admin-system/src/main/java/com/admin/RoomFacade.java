//package com.admin;
//
//import dz.eadn.admin.business.entity.Fichier;
//import dz.eadn.appconfig.ConfigUtils;
//import dz.eadn.common.business.AbstractFacade;
//import dz.eadn.pemp.business.repository.FichierRepository;
//
//import javax.ejb.Stateless;
//import javax.inject.Inject;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
///**
// *
// * @author CHEKOR Samir <samir.chekor@eadn.dz>
// */
//
//@Stateless
//public class RoomFacade extends AbstractFacade<Fichier> {
//
//    @PersistenceContext(name = ConfigUtils.ADMINPU)
//    private EntityManager em;
//
//    @Inject
//    private FichierRepository fichierRepository;
//
//    @Override
//    protected EntityManager getEntityManager() {
//        return em;
//    }
//
//    public RoomFacade() {
//        super(Fichier.class);
//    }
//
//    public Fichier getFichierByUuid(String uuid){
//        return fichierRepository.findByUuid(uuid);
//    }
//
//    public Fichier getFichierByfUuidAndTypeFichierLikeAndNomEntiteFichier(String uuid,String typeFichier,String nomEntiteFichier){
//        return fichierRepository.findByUuidAndTypeFichierLikeAndNomEntiteFichier(uuid,typeFichier,nomEntiteFichier);
//    }
//
//    public Fichier getFichierByfUuidAndTypeFichierLike(String uuid,String typeFichier){
//        return fichierRepository.findByUuidAndTypeFichierLike(uuid,typeFichier);
//    }
//
//
//}
