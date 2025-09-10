package sv.com.jsoft.util;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

public abstract class EntityPanache extends PanacheEntityBase implements Identificador<Long> {

    public void setActivo(Boolean activo) {

    }

    @Override
    public Long getID(){
        return 0l;
    }

    public void save(PanacheEntityBase entityBase){
        entityBase.persist();
    }

    public PanacheEntityBase findByID(Integer id) {
        return PanacheEntityBase.findById(id);
    }

}
