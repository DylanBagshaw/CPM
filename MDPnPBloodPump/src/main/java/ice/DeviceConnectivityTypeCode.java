
/*
  WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

  This file was generated from .idl using "rtiddsgen".
  The rtiddsgen tool is part of the RTI Connext distribution.
  For more information, type 'rtiddsgen -help' at a command shell
  or consult the RTI Connext manual.
*/
    
package ice;
        
import com.rti.dds.typecode.*;


public class DeviceConnectivityTypeCode {
    public static final TypeCode VALUE = getTypeCode();

    private static TypeCode getTypeCode() {
        TypeCode tc = null;
        int i=0;
        StructMember sm[] = new StructMember[5];

        sm[i]=new StructMember("unique_device_identifier",false,(short)-1,true,(TypeCode)ice.UniqueDeviceIdentifierTypeCode.VALUE,0,false); i++;
        sm[i]=new StructMember("state",false,(short)-1,false,(TypeCode)ice.ConnectionStateTypeCode.VALUE,1,false); i++;
        sm[i]=new StructMember("type",false,(short)-1,false,(TypeCode)ice.ConnectionTypeTypeCode.VALUE,2,false); i++;
        sm[i]=new StructMember("info",false,(short)-1,false,(TypeCode)ice.LongStringTypeCode.VALUE,3,false); i++;
        sm[i]=new StructMember("valid_targets",false,(short)-1,false,(TypeCode)ice.ValidTargetsTypeCode.VALUE,4,false); i++;

        tc = TypeCodeFactory.TheTypeCodeFactory.create_struct_tc("ice::DeviceConnectivity",ExtensibilityKind.EXTENSIBLE_EXTENSIBILITY,sm);
        return tc;
    }
}
