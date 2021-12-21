package com.kevvlvl.quarkus.repository;

import com.kevvlvl.quarkus.model.Make;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MakeRepository implements PanacheRepository<Make> {
}
