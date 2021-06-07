package delta.games.lotro.kinship.io.xml;

import java.io.File;

import javax.xml.transform.sax.TransformerHandler;

import org.xml.sax.helpers.AttributesImpl;

import delta.common.utils.io.xml.XmlFileWriterHelper;
import delta.common.utils.io.xml.XmlWriter;
import delta.games.lotro.common.id.InternalGameId;
import delta.games.lotro.kinship.KinshipSummary;

/**
 * Writes LOTRO kinship summaries to XML files.
 * @author DAM
 */
public class KinshipSummaryXMLWriter
{
  /**
   * Write a kinship summary to a XML file.
   * @param outFile Output file.
   * @param summary Summary to write.
   * @param encoding Encoding to use.
   * @return <code>true</code> if it succeeds, <code>false</code> otherwise.
   */
  public boolean write(File outFile, final KinshipSummary summary, String encoding)
  {
    XmlWriter writer=new XmlWriter()
    {
      @Override
      public void writeXml(TransformerHandler hd) throws Exception
      {
        AttributesImpl kinshipAttrs=new AttributesImpl();
        write(kinshipAttrs,summary);
        hd.startElement("","",KinshipXMLConstants.KINSHIP_TAG,kinshipAttrs);
        hd.endElement("","",KinshipXMLConstants.KINSHIP_TAG);
      }
    };
    XmlFileWriterHelper helper=new XmlFileWriterHelper();
    boolean ret=helper.write(outFile,encoding,writer);
    return ret;
  }

  /**
   * Write kinship summary attributes.
   * @param kinshipAttrs Attributes to write to.
   * @param kinship Source data.
   * @throws Exception If an error occurs.
   */
  public static void write(AttributesImpl kinshipAttrs, KinshipSummary kinship) throws Exception
  {
    // ID
    InternalGameId kinshipID=kinship.getKinshipID();
    if (kinshipID!=null)
    {
      String kinshipIDStr=kinshipID.asPersistedString();
      kinshipAttrs.addAttribute("","",KinshipXMLConstants.KINSHIP_ID_ATTR,XmlWriter.CDATA,kinshipIDStr);
    }
    // Name
    String name=kinship.getName();
    if ((name!=null) && (name.length()>0))
    {
      kinshipAttrs.addAttribute("","",KinshipXMLConstants.KINSHIP_NAME_ATTR,XmlWriter.CDATA,name);
    }
    // Leader ID
    InternalGameId leaderID=kinship.getLeaderID();
    if (leaderID!=null)
    {
      String leaderIDStr=leaderID.asPersistedString();
      kinshipAttrs.addAttribute("","",KinshipXMLConstants.KINSHIP_LEADER_ID_ATTR,XmlWriter.CDATA,leaderIDStr);
    }
  }
}