package nth.FingerPrint.weixin.entity.Message.req;

/** 
 * ������Ϣ 
 *  
 * @author Caspar 
 *  
 */  
public class VoiceMessage extends BaseMessage {  
    /** 
     * ý��ID 
     */  
    private String MediaId;  
    /** 
     * ������ʽ 
     */  
    private String Format;  
  
    public String getMediaId() {  
        return MediaId;  
    }  
  
    public void setMediaId(String mediaId) {  
        MediaId = mediaId;  
    }  
  
    public String getFormat() {  
        return Format;  
    }  
  
    public void setFormat(String format) {  
        Format = format;  
    }  
  
}  