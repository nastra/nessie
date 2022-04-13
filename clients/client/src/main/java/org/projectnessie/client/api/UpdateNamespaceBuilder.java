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
package org.projectnessie.client.api;

import java.util.Map;
import java.util.Set;
import org.projectnessie.error.NessieNamespaceNotFoundException;
import org.projectnessie.error.NessieReferenceNotFoundException;
import org.projectnessie.model.Namespace;

/**
 * Request builder for updating properties of a {@link Namespace}.
 *
 * @since {@link NessieApiV1}
 */
public interface UpdateNamespaceBuilder extends OnNamespaceBuilder<UpdateNamespaceBuilder> {

  UpdateNamespaceBuilder updateProperty(String key, String value);

  UpdateNamespaceBuilder removeProperty(String key);

  UpdateNamespaceBuilder updateProperties(Map<String, String> propertyUpdates);

  UpdateNamespaceBuilder removeProperties(Set<String> propertyRemovals);

  void update() throws NessieNamespaceNotFoundException, NessieReferenceNotFoundException;
}
