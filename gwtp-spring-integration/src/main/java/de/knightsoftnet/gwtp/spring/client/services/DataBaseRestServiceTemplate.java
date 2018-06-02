/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package de.knightsoftnet.gwtp.spring.client.services;

import com.gwtplatform.dispatch.rest.shared.RestAction;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import java.io.Serializable;

/**
 * Client side interface for generic CRUD operations on a repository for a specific type.
 *
 * @author Manfred Tremmel
 */
public interface DataBaseRestServiceTemplate<T, ID extends Serializable> {

  /**
   * Saves a given entity. Use the returned instance for further operations as the save operation
   * might have changed the entity instance completely.
   *
   * @param pentity to save
   * @return the saved entity
   */
  @POST
  RestAction<T> save(T pentity);

  /**
   * Saves all given entities.
   *
   * @param pentities list of entities to save
   * @return the saved entities
   * @throws IllegalArgumentException in case the given entity is {@literal null}.
   */
  @PUT
  RestAction<Iterable<T>> save(Iterable<T> pentities);

  /**
   * Retrieves an entity by its id.
   *
   * @param pid must not be {@literal null}.
   * @return the entity with the given id or {@literal null} if none found
   * @throws IllegalArgumentException if {@code id} is {@literal null}
   */
  @GET
  @Path("/{id}")
  RestAction<T> findOne(@PathParam("id") ID pid);

  /**
   * Returns whether an entity with the given id exists.
   *
   * @param id must not be {@literal null}.
   * @return true if an entity with the given id exists, {@literal false} otherwise
   * @throws IllegalArgumentException if {@code id} is {@literal null}
   */
  // RestAction<Boolean> exists(ID id);

  /**
   * Returns all instances of the type.
   *
   * @return all entities
   */
  @GET
  RestAction<Iterable<T>> findAll();

  /**
   * Returns all instances of the type with the given IDs.
   *
   * @param ids list of ids to search for
   * @return list entries
   */
  // RestAction<Iterable<T>> findAll(Iterable<ID> ids);

  /**
   * Returns the number of entities available.
   *
   * @return the number of entities
   */
  // RestAction<Long> count();

  /**
   * Deletes the entity with the given id.
   *
   * @param pid must not be {@literal null}.
   * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
   */
  @DELETE
  @Path("/{id}")
  RestAction<Void> delete(@PathParam("id") ID pid);

  /**
   * Deletes a given entity.
   *
   * @param entity to delete
   * @throws IllegalArgumentException in case the given entity is {@literal null}.
   */
  // @DELETE
  // RestAction<Void> delete(T entity);

  /**
   * Deletes the given entities.
   *
   * @param entities list of entities to delete
   * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
   */
  // @DELETE
  // RestAction<Void> delete(Iterable<? extends T> entities);

  /**
   * Deletes all entities managed by the repository.
   */
  @DELETE
  RestAction<Void> deleteAll();

}
