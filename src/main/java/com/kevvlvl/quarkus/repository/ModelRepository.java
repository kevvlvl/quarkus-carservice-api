package com.kevvlvl.quarkus.repository;

import com.kevvlvl.quarkus.model.Model;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ModelRepository implements PanacheRepository<Model> {
}
