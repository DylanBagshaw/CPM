
/*
  WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

  This file was generated from .idl using "rtiddsgen".
  The rtiddsgen tool is part of the RTI Connext distribution.
  For more information, type 'rtiddsgen -help' at a command shell
  or consult the RTI Connext manual.
*/
    
package ice;
        
import com.rti.dds.typecode.*;


public class DeviceIdentityTypeCode {
    public static final TypeCode VALUE = getTypeCode();

    private static TypeCode getTypeCode() {
        TypeCode tc = null;
        int i=0;
        StructMember sm[] = new StructMember[7];

        sm[i]=new StructMember("unique_device_identifier",false,(short)-1,true,(TypeCode)ice.UniqueDeviceIdentifierTypeCode.VALUE,0,false); i++;
        sm[i]=new StructMember("manufacturer",false,(short)-1,false,(TypeCode)ice.LongStringTypeCode.VALUE,1,false); i++;
        sm[i]=new StructMember("model",false,(short)-1,false,(TypeCode)ice.LongStringTypeCode.VALUE,2,false); i++;
        sm[i]=new StructMember("serial_number",false,(short)-1,false,(TypeCode)ice.LongStringTypeCode.VALUE,3,false); i++;
        sm[i]=new StructMember("icon",false,(short)-1,false,(TypeCode)ice.ImageTypeCode.VALUE,4,false); i++;
        sm[i]=new StructMember("build",false,(short)-1,false,(TypeCode)new TypeCode(TCKind.TK_STRING,128),5,false); i++;
        sm[i]=new StructMember("operating_system",false,(short)-1,false,(TypeCode)new TypeCode(TCKind.TK_STRING,128),6,false); i++;

        tc = TypeCodeFactory.TheTypeCodeFactory.create_struct_tc("ice::DeviceIdentity",ExtensibilityKind.EXTENSIBLE_EXTENSIBILITY,sm);
        return tc;
    }
}
