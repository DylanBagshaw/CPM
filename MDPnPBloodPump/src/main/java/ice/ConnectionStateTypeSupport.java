
/*
  WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

  This file was generated from .idl using "rtiddsgen".
  The rtiddsgen tool is part of the RTI Connext distribution.
  For more information, type 'rtiddsgen -help' at a command shell
  or consult the RTI Connext manual.
*/
    
package ice;
        

import com.rti.dds.cdr.CdrEncapsulation;
import com.rti.dds.cdr.CdrInputStream;
import com.rti.dds.cdr.CdrOutputStream;
import com.rti.dds.cdr.CdrPrimitiveType;
import com.rti.dds.cdr.CdrBuffer;
import com.rti.dds.cdr.CdrHelper;
import com.rti.dds.cdr.CdrMemberInfo;
import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.publication.DataWriter;
import com.rti.dds.publication.DataWriterListener;
import com.rti.dds.subscription.DataReader;
import com.rti.dds.subscription.DataReaderListener;
import com.rti.dds.topic.KeyHash_t;
import com.rti.dds.topic.TypeSupportImpl;
import com.rti.dds.topic.TypeSupportType;
import com.rti.dds.util.Sequence;
import com.rti.dds.topic.DefaultEndpointData;
import com.rti.dds.infrastructure.RETCODE_ERROR;

    
import com.rti.dds.infrastructure.*;
import com.rti.dds.topic.TypeSupportParticipantInfo;
import com.rti.dds.topic.TypeSupportEndpointInfo;
import com.rti.dds.typecode.TypeCode;
import com.rti.dds.cdr.IllegalCdrStateException;


import com.rti.dds.infrastructure.Copyable;
    

/**
 * A collection of useful methods for dealing with objects of type
 * ConnectionState.
 */
public class ConnectionStateTypeSupport extends TypeSupportImpl {    
    // -----------------------------------------------------------------------
    // Private Fields
    // -----------------------------------------------------------------------

    private static final String TYPE_NAME = "ice::ConnectionState";

    private static final char[] PLUGIN_VERSION = {2, 0, 0, 0};

    private static final ConnectionStateTypeSupport _singleton
        = new ConnectionStateTypeSupport();    

    // -----------------------------------------------------------------------
    // Public Methods
    // -----------------------------------------------------------------------

    // --- External methods: -------------------------------------------------
    /* The methods in this section are for use by users of RTI Connext
     */

    public static String get_type_name() {
        return _singleton.get_type_nameI();
    }

    // --- Internal methods: -------------------------------------------------
    /* The methods in this section are for use by RTI Connext
     * itself and by the code generated by rtiddsgen for other types.
     * They should be used directly or modified only by advanced users and are
     * subject to change in future versions of RTI Connext.
     */

    public static ConnectionStateTypeSupport get_instance() {
        return _singleton;
    }

    /* deprecated API */
    public static ConnectionStateTypeSupport getInstance() {
        return get_instance();
    }
    
    /* Doesn't make sense for an enum to create a instance without a value*/ 
    public Object create_data() {
        return null;
    }

    public void destroy_data(Object data) {
        return;
    }

    /* This is a concrete implementation of this method inherited from the base class.
     * While this implementation is not strictly a copy it can have the same effect. 
     * In order to use it properly, assign the result of the operation to the member 
     * that is the target of the copy.
     * So, for example:
     *    myEnumField = ConnectionStateTypeSupport.get_instance().copy_data(anotherInstanceOfEnum);
     * Since <code>Enum</code>s are immutable there cannot be a true copy 
     * made but this method will return a reference to the same enumerate as 
     * <code>source</code>.
     * @return returns <code>source</code>
     */
    public Object copy_data(Object destination, Object source) {
        return source;
    }
    
    public Class get_type() {
        return ConnectionState.class;
    }

    
    public void serialize(Object endpoint_data,Object src, CdrOutputStream dst, boolean serialize_encapsulation, short encapsulation_id, boolean serialize_sample, Object endpoint_plugin_qos) {
        int position = 0;
ConnectionState typedSrc = (ConnectionState) src;   
        if(serialize_encapsulation) { 
          dst.serializeAndSetCdrEncapsulation(encapsulation_id);


          position = dst.resetAlignment();

        }
        if(serialize_sample) {

          dst.writeInt(typedSrc.ordinal());

        }


        if (serialize_encapsulation) {
          dst.restoreAlignment(position);
        }

    }
    
    
    public Object deserialize_sample(
        Object endpoint_data,Object dst, 
        CdrInputStream src, boolean deserialize_encapsulation, 
        boolean deserialize_sample,Object endpoint_plugin_qos) {
        int position = 0;


        // ignore dst since we are dealing with an immutable object
        if(deserialize_encapsulation) {
          src.deserializeAndSetCdrEncapsulation();


          position = src.resetAlignment();

        }

        if(deserialize_sample) {

            int ordinal = src.readInt();
            dst = ConnectionState.valueOf(ordinal);
            
            if (dst == null) {
                throw new IllegalArgumentException(
                    "invalid enumerator " + ordinal);
            
            }
            

        }


        if (deserialize_encapsulation) {
          src.restoreAlignment(position);
        }


        return dst;
    }

    
    public void skip(Object endpoint_data, 
                     CdrInputStream src,
                     boolean skip_encapsulation, 
                     boolean skip_sample, 
                     Object endpoint_plugin_qos)
    {
        int position = 0;


        if (skip_encapsulation) {
            src.skipEncapsulation();


            position = src.resetAlignment();

        }

        if (skip_sample) {

            src.skipInt();

        }


        if (skip_encapsulation) {
          src.restoreAlignment(position);
        }

    }

    
    public long get_serialized_sample_max_size(Object endpoint_data,boolean include_encapsulation,short encapsulation_id,long currentAlignment) {

        long encapsulation_size = currentAlignment;
 
        long origAlignment = currentAlignment;

        if (include_encapsulation) {
            if (!CdrEncapsulation.isValidEncapsulationKind(encapsulation_id)) {
                throw new RETCODE_ERROR("Unsupported encapsulation");
            }


            encapsulation_size += CdrPrimitiveType.SHORT.getMaxSizeSerialized(encapsulation_size);
            encapsulation_size += CdrPrimitiveType.SHORT.getMaxSizeSerialized(encapsulation_size);
            encapsulation_size -= currentAlignment;
            currentAlignment = 0;
            origAlignment = 0;

        }

        currentAlignment += CdrPrimitiveType.INT.getMaxSizeSerialized(currentAlignment);


        if (include_encapsulation) {
            currentAlignment += encapsulation_size;
        }


        return currentAlignment - origAlignment;
    }

    
    public long get_serialized_sample_min_size(Object endpoint_data,boolean include_encapsulation,short encapsulation_id,long currentAlignment) {
        return get_serialized_sample_max_size(endpoint_data,include_encapsulation,encapsulation_id,currentAlignment);
    }

    
    public long get_serialized_sample_size(
	Object endpoint_data, boolean include_encapsulation, 
        short encapsulation_id, long current_alignment,
	Object sample) 
    {
        return get_serialized_sample_max_size(endpoint_data,include_encapsulation,encapsulation_id,current_alignment);
    }

    
    public void serialize_key(
        Object endpoint_data,
        Object src,
        CdrOutputStream dst,
        boolean serialize_encapsulation,
        short encapsulation_id,
        boolean serialize_key,
        Object endpoint_plugin_qos) 
    {
        serialize(endpoint_data, src, dst, serialize_encapsulation, encapsulation_id, serialize_key, endpoint_plugin_qos);
    }
    
    
    public Object deserialize_key_sample(
        Object endpoint_data,
        Object dst,
        CdrInputStream src,
        boolean deserialize_encapsulation,
        boolean deserialize_key,
        Object endpoint_plugin_qos) 
    {
        return deserialize_sample(endpoint_data, dst, src, deserialize_encapsulation, deserialize_key, endpoint_plugin_qos);
    }
    
    
    public long get_serialized_key_max_size(
        Object endpoint_data,
        boolean include_encapsulation,
        short encapsulation_id,
        long currentAlignment) 
    {
        return get_serialized_sample_max_size(endpoint_data,include_encapsulation,encapsulation_id,currentAlignment);
    }

    
    public Object serialized_sample_to_key(
        Object endpoint_data,
        Object sample,
        CdrInputStream src, 
        boolean deserialize_encapsulation,  
        boolean deserialize_key, 
        Object endpoint_plugin_qos) 
    {    
        return deserialize_sample(
            endpoint_data,sample,src,deserialize_encapsulation,deserialize_key,
            endpoint_plugin_qos);
    }

    // -----------------------------------------------------------------------
    // Protected Methods
    // -----------------------------------------------------------------------

    protected DataWriter create_datawriter(long native_writer,
                                           DataWriterListener listener,
                                           int mask) {
        return null;
    }

    protected DataReader create_datareader(long native_reader,
                                           DataReaderListener listener,
                                           int mask) {
        return null;
    }

    // -----------------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------------

    protected ConnectionStateTypeSupport() {
        
            super(TYPE_NAME, false /*no key*/,ConnectionStateTypeCode.VALUE,ConnectionState.class,TypeSupportType.TST_ENUM, PLUGIN_VERSION);
                
    }
}
