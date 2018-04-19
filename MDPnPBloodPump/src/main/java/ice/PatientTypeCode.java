
/*
  WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

  This file was generated from .idl using "rtiddsgen".
  The rtiddsgen tool is part of the RTI Connext distribution.
  For more information, type 'rtiddsgen -help' at a command shell
  or consult the RTI Connext manual.
*/
    
package ice;
        
import com.rti.dds.typecode.*;


public class PatientTypeCode {
    public static final TypeCode VALUE = getTypeCode();

    private static TypeCode getTypeCode() {
        TypeCode tc = null;
        int i=0;
        StructMember sm[] = new StructMember[3];

        sm[i]=new StructMember("mrn",false,(short)-1,true,(TypeCode)new TypeCode(TCKind.TK_STRING,16),0,false); i++;
        sm[i]=new StructMember("given_name",false,(short)-1,false,(TypeCode)new TypeCode(TCKind.TK_WSTRING,256),1,false); i++;
        sm[i]=new StructMember("family_name",false,(short)-1,false,(TypeCode)new TypeCode(TCKind.TK_WSTRING,256),2,false); i++;

        tc = TypeCodeFactory.TheTypeCodeFactory.create_struct_tc("ice::Patient",ExtensibilityKind.EXTENSIBLE_EXTENSIBILITY,sm);
        return tc;
    }
}
