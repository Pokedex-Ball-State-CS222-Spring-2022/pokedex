package edu.bsu.cs222.model;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.util.LinkedHashMap;
import java.util.List;

public class TypeBuilder {

    public Type createType(String name, Object typeJsonObject) {
        TypeParser typeParser = new TypeParser();
        LinkedHashMap<Class<?>, Class<?>> damageRelationsMap = createRootOfDamageRelations(typeJsonObject);

        List<String> weakToList = typeParser.parseWeakTo(damageRelationsMap);
        List<String> resistantToList = typeParser.parseResistantTo(damageRelationsMap);
        List<String> immuneToList = typeParser.parseImmuneTo(damageRelationsMap);

        return new Type(name, weakToList, resistantToList, immuneToList);
    }

    private LinkedHashMap<Class<?>, Class<?>> createRootOfDamageRelations(Object typeJsonDocument) {
        JSONArray pastDamageRelationsArray = JsonPath.read(typeJsonDocument,"$.past_damage_relations");
        LinkedHashMap<Class<?>, Class<?>> yellowDamageRelationsMap;
        if (pastDamageRelationsArray.size() != 0) {
            // This solution only works for gen 1
            yellowDamageRelationsMap = JsonPath.read(typeJsonDocument, "$.past_damage_relations[0].damage_relations");
        } else {
            yellowDamageRelationsMap = JsonPath.read(typeJsonDocument, "$.damage_relations");
        }
        return yellowDamageRelationsMap;
    }
}
