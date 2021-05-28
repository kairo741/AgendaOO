package DAO;

import EMF.EMFProducer;
import entity.Contato;

import javax.persistence.EntityManager;

public class DaoContatoImpl implements DaoContato {

    private EntityManager getEntityManager(){
        return EMFProducer.getEmf().createEntityManager();
    }
    @Override
    public void salvarOuAtualizar(Contato obj) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            if (obj.getId() == null) {
                em.persist(obj);
            } else {
                em.merge(obj);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        closeEntityManager(em);
    }


    private void closeEntityManager(EntityManager e){
        if (e.isOpen()){
            e.close();
        }
    }

}
