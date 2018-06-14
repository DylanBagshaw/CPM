
/*
  WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

  This file was generated from .idl using "rtiddsgen".
  The rtiddsgen tool is part of the RTI Connext distribution.
  For more information, type 'rtiddsgen -help' at a command shell
  or consult the RTI Connext manual.
*/
    
package ice;
        
import com.rti.dds.typecode.*;


public class BypassStatusTypeCode {
    public static final TypeCode VALUE = getTypeCode();

    private static TypeCode getTypeCode() {
        TypeCode tc = null;
        int i=0;
        StructMember sm[] = new StructMember[8];

        sm[i]=new StructMember("unique_device_identifier",false,(short)-1,true,(TypeCode)ice.UniqueDeviceIdentifierTypeCode.VALUE,0,false); i++;
        sm[i]=new StructMember("bypassActive",false,(short)-1,false,(TypeCode)TypeCode.TC_BOOLEAN,1,false); i++;
        sm[i]=new StructMember("bypass_flow_lmin",false,(short)-1,false,(TypeCode)TypeCode.TC_DOUBLE,2,false); i++;
        sm[i]=new StructMember("bypass_speed_rpm",false,(short)-1,false,(TypeCode)TypeCode.TC_DOUBLE,3,false); i++;
        sm[i]=new StructMember("blood_temp_celsius",false,(short)-1,false,(TypeCode)TypeCode.TC_DOUBLE,4,false); i++;
        sm[i]=new StructMember("blood_press_mmhg",false,(short)-1,false,(TypeCode)TypeCode.TC_DOUBLE,5,false); i++;
        sm[i]=new StructMember("volume_bypassed_ml",false,(short)-1,false,(TypeCode)TypeCode.TC_DOUBLE,6,false); i++;
        sm[i]=new StructMember("bypass_duration_seconds",false,(short)-1,false,(TypeCode)TypeCode.TC_LONG,7,false); i++;

        tc = TypeCodeFactory.TheTypeCodeFactory.create_struct_tc("ice::BypassStatus",ExtensibilityKind.EXTENSIBLE_EXTENSIBILITY,sm);
        return tc;
    }
}
