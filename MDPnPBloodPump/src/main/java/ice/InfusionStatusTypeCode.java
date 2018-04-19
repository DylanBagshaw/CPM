
/*
  WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

  This file was generated from .idl using "rtiddsgen".
  The rtiddsgen tool is part of the RTI Connext distribution.
  For more information, type 'rtiddsgen -help' at a command shell
  or consult the RTI Connext manual.
*/
    
package ice;
        
import com.rti.dds.typecode.*;


public class InfusionStatusTypeCode {
    public static final TypeCode VALUE = getTypeCode();

    private static TypeCode getTypeCode() {
        TypeCode tc = null;
        int i=0;
        StructMember sm[] = new StructMember[8];

        sm[i]=new StructMember("unique_device_identifier",false,(short)-1,true,(TypeCode)ice.UniqueDeviceIdentifierTypeCode.VALUE,0,false); i++;
        sm[i]=new StructMember("infusionActive",false,(short)-1,false,(TypeCode)TypeCode.TC_BOOLEAN,1,false); i++;
        sm[i]=new StructMember("drug_name",false,(short)-1,false,(TypeCode)new TypeCode(TCKind.TK_WSTRING,128),2,false); i++;
        sm[i]=new StructMember("drug_mass_mcg",false,(short)-1,false,(TypeCode)TypeCode.TC_LONG,3,false); i++;
        sm[i]=new StructMember("solution_volume_ml",false,(short)-1,false,(TypeCode)TypeCode.TC_LONG,4,false); i++;
        sm[i]=new StructMember("volume_to_be_infused_ml",false,(short)-1,false,(TypeCode)TypeCode.TC_LONG,5,false); i++;
        sm[i]=new StructMember("infusion_duration_seconds",false,(short)-1,false,(TypeCode)TypeCode.TC_LONG,6,false); i++;
        sm[i]=new StructMember("infusion_fraction_complete",false,(short)-1,false,(TypeCode)TypeCode.TC_FLOAT,7,false); i++;

        tc = TypeCodeFactory.TheTypeCodeFactory.create_struct_tc("ice::InfusionStatus",ExtensibilityKind.EXTENSIBLE_EXTENSIBILITY,sm);
        return tc;
    }
}
