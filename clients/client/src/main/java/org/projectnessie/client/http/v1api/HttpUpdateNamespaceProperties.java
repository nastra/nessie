/*
 * Copyright (C) 2020 Dremio
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
package org.projectnessie.client.http.v1api;

import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import org.projectnessie.api.params.ImmutableNamespacePropertyUpdate;
import org.projectnessie.api.params.NamespaceParams;
import org.projectnessie.api.params.NamespaceParamsBuilder;
import org.projectnessie.client.api.UpdateNamespacePropertiesBuilder;
import org.projectnessie.client.http.NessieApiClient;
import org.projectnessie.error.NessieNamespaceNotFoundException;
import org.projectnessie.error.NessieReferenceNotFoundException;
import org.projectnessie.model.Namespace;

final class HttpUpdateNamespaceProperties extends BaseHttpRequest
    implements UpdateNamespacePropertiesBuilder {

  private final NamespaceParamsBuilder builder = NamespaceParams.builder();
  private final ImmutableNamespacePropertyUpdate.Builder updateBuilder =
      ImmutableNamespacePropertyUpdate.builder();

  HttpUpdateNamespaceProperties(NessieApiClient client) {
    super(client);
  }

  @Override
  public UpdateNamespacePropertiesBuilder namespace(Namespace namespace) {
    builder.namespace(namespace);
    return this;
  }

  @Override
  public UpdateNamespacePropertiesBuilder refName(String refName) {
    builder.refName(refName);
    return this;
  }

  @Override
  public UpdateNamespacePropertiesBuilder hashOnRef(@Nullable String hashOnRef) {
    builder.hashOnRef(hashOnRef);
    return this;
  }

  @Override
  public UpdateNamespacePropertiesBuilder propertyRemovals(List<String> propertyRemovals) {
    updateBuilder.propertyRemovals(propertyRemovals);
    return this;
  }

  @Override
  public UpdateNamespacePropertiesBuilder propertyUpdates(Map<String, String> propertyUpdates) {
    updateBuilder.propertyUpdates(propertyUpdates);
    return this;
  }

  @Override
  public void update() throws NessieNamespaceNotFoundException, NessieReferenceNotFoundException {
    client.getNamespaceApi().updateProperties(builder.build(), updateBuilder.build());
  }
}
