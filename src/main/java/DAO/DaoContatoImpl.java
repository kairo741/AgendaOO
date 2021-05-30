package DAO;

import EMF.EMFProducer;
import entity.Contato;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.persistence.EntityManager;
import java.util.List;

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

    @Override
    public List<Contato> getContato(String nome){
        EntityManager em = getEntityManager();

        List<Contato> resultado = em.createQuery("SELECT x FROM Contato x WHERE x.nome = :nome_contato ")
                .setParameter("nome_contato", nome)
                .getResultList();

        closeEntityManager(em);
        return resultado;

    }

    public void deletarContato(Long id){
        EntityManager em = getEntityManager();

        ObjectMapper objectMapper = new ObjectMapper();
        Contato contato = objectMapper.convertValue(
                em.createQuery("SELECT x FROM Contato x WHERE x.id = :id ")
                .setParameter("id", id)
                .getSingleResult(), Contato.class);

        em.remove(contato);

        closeEntityManager(em);
    }

    private void closeEntityManager(EntityManager e){
        if (e.isOpen()){
            e.close();
        }
    }

}
