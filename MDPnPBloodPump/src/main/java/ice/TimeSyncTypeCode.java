
/*
  WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

  This file was generated from .idl using "rtiddsgen".
  The rtiddsgen tool is part of the RTI Connext distribution.
  For more information, type 'rtiddsgen -help' at a command shell
  or consult the RTI Connext manual.
*/
    
package ice;
        
import com.rti.dds.typecode.*;


public class TimeSyncTypeCode {
    public static final TypeCode VALUE = getTypeCode();

    private static TypeCode getTypeCode() {
        TypeCode tc = null;
        int i=0;
        StructMember sm[] = new StructMember[4];

        sm[i]=new StructMember("heartbeat_source",false,(short)-1,true,(TypeCode)ice.UniqueDeviceIdentifierTypeCode.VALUE,0,false); i++;
        sm[i]=new StructMember("heartbeat_recipient",false,(short)-1,true,(TypeCode)ice.UniqueDeviceIdentifierTypeCode.VALUE,1,false); i++;
        sm[i]=new StructMember("source_source_timestamp",false,(short)-1,false,(TypeCode)ice.Time_tTypeCode.VALUE,2,false); i++;
        sm[i]=new StructMember("recipient_receipt_timestamp",false,(short)-1,false,(TypeCode)ice.Time_tTypeCode.VALUE,3,false); i++;

        tc = TypeCodeFactory.TheTypeCodeFactory.create_struct_tc("ice::TimeSync",ExtensibilityKind.EXTENSIBLE_EXTENSIBILITY,sm);
        return tc;
    }
}
