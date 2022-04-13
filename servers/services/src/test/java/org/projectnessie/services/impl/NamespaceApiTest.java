/*
 * Copyright (C) 2022 Dremio
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.projectnessie.services.impl;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.projectnessie.api.params.ImmutableNamespaceUpdate;
import org.projectnessie.api.params.NamespaceParams;
import org.projectnessie.model.Namespace;

@Execution(ExecutionMode.CONCURRENT)
public class NamespaceApiTest {

  @Test
  public void emptyNamespaceCreation() {
    NamespaceApiImpl api = new NamespaceApiImpl(null, null, null, null);
    assertThatThrownBy(
            () ->
                api.createNamespace(
                    NamespaceParams.builder().refName("main").namespace(Namespace.EMPTY).build(),
                    ImmutableNamespaceUpdate.builder().build()))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Namespace name must not be empty");
  }

  @Test
  public void namespaceCreationWithPropertyRemoval() {
    NamespaceApiImpl api = new NamespaceApiImpl(null, null, null, null);
    assertThatThrownBy(
            () ->
                api.createNamespace(
                    NamespaceParams.builder().refName("main").namespace(Namespace.of("a")).build(),
                    ImmutableNamespaceUpdate.builder().addPropertyRemovals("key").build()))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Cannot remove properties during Namespace creation");
  }
}
