/*
 * Copyright 2010 Google Inc. Copyright 2016 Manfred Tremmel
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package javax.validation;

import com.google.gwt.user.client.rpc.CustomFieldSerializer;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

import java.util.Set;

/**
 * Custom Serializer for {@link ConstraintViolationException}.
 */
public class ConstraintViolationException_CustomFieldSerializer
    extends CustomFieldSerializer<ConstraintViolationException> {

  @SuppressWarnings("unused")
  public static void deserialize(final SerializationStreamReader streamReader,
      final ConstraintViolationException instance) throws SerializationException {
    // no fields
  }

  public static ConstraintViolationException instantiate(
      final SerializationStreamReader streamReader) throws SerializationException {
    final String message = streamReader.readString();
    @SuppressWarnings("unchecked")
    final Set<ConstraintViolation<?>> set = (Set<ConstraintViolation<?>>) streamReader.readObject();
    return new ConstraintViolationException(message, set);
  }

  public static void serialize(final SerializationStreamWriter streamWriter,
      final ConstraintViolationException instance) throws SerializationException {
    streamWriter.writeString(instance.getMessage());
    streamWriter.writeObject(instance.getConstraintViolations());
  }

  @Override
  public void deserializeInstance(final SerializationStreamReader streamReader,
      final ConstraintViolationException instance) throws SerializationException {
    deserialize(streamReader, instance);
  }

  @Override
  public boolean hasCustomInstantiateInstance() {
    return true;
  }

  @Override
  public ConstraintViolationException instantiateInstance(
      final SerializationStreamReader streamReader) throws SerializationException {
    return instantiate(streamReader);
  }

  @Override
  public void serializeInstance(final SerializationStreamWriter streamWriter,
      final ConstraintViolationException instance) throws SerializationException {
    serialize(streamWriter, instance);
  }
}
