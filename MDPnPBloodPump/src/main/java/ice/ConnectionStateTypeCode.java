
/*
  WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

  This file was generated from .idl using "rtiddsgen".
  The rtiddsgen tool is part of the RTI Connext distribution.
  For more information, type 'rtiddsgen -help' at a command shell
  or consult the RTI Connext manual.
*/
    
package ice;
        
import com.rti.dds.typecode.*;


public class ConnectionStateTypeCode {
    public static final TypeCode VALUE = getTypeCode();

    private static TypeCode getTypeCode() {
        TypeCode tc = null;
        int i=0;
        EnumMember em[] = new EnumMember[5];

        int[] ordinals = ConnectionState.getOrdinals();
        for (i=0;i<5;i++) {
            em[i]=new EnumMember(ConnectionState.valueOf(ordinals[i]).toString(),ordinals[i]);
        }

        tc = TypeCodeFactory.TheTypeCodeFactory.create_enum_tc("ice::ConnectionState",ExtensibilityKind.EXTENSIBLE_EXTENSIBILITY,em);
        return tc;
    }
}
