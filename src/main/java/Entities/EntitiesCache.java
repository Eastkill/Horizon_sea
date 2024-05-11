package Entities;

import Entities.Assets.*;

import java.util.Map;

public final class EntitiesCache {
    private static final Map<Integer,Entity> map = Map.of(
            0,new Water(),
            1,new Ground(),
            2,new City()
    );
    private EntitiesCache(){}
    public static Entity getEntity(int id){
    Entity entity = map.get(id);
    if(entity == null){
        throw new IllegalArgumentException("Nie istnieje obiekt o id:"+id);
    }
    return(Entity) entity.clone();
    }
}
