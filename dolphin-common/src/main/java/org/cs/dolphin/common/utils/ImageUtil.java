package org.cs.dolphin.common.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;


public class ImageUtil
{

	
	/**
	 * 重置图片尺寸
	 * @param originalFile
	 * @param resizedFile
	 * @param newWidth
	 * @param newHeight
	 * @param quality
	 * @throws IOException
	 */
	public static void resize(File originalFile, File resizedFile,
			int newWidth,int newHeight, float quality) throws IOException {
		if (quality > 1) {
			throw new IllegalArgumentException(
					"Quality has to be between 0 and 1");
		}

		ImageIcon ii = new ImageIcon(originalFile.getCanonicalPath());
		Image i = ii.getImage();
		Image resizedImage = null;

		int iWidth = i.getWidth(null);
		int iHeight = i.getHeight(null);

//		if (iWidth > iHeight) {
//			resizedImage = i.getScaledInstance(newWidth, (newWidth * iHeight)
//					/ iWidth, Image.SCALE_SMOOTH);
//		} else {
//			resizedImage = i.getScaledInstance((newWidth * iWidth) / iHeight,
//					newWidth, Image.SCALE_SMOOTH);
//		}
		resizedImage = i.getScaledInstance(newWidth,
				newHeight, Image.SCALE_SMOOTH);

		// This code ensures that all the pixels in the image are loaded.
		Image temp = new ImageIcon(resizedImage).getImage();

		// Create the buffered image.
		BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null),
				temp.getHeight(null), BufferedImage.TYPE_INT_RGB);

		// Copy image to buffered image.
		Graphics g = bufferedImage.createGraphics();

		// Clear background and paint the image.
		g.setColor(Color.white);
		g.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));
		g.drawImage(temp, 0, 0, null);
		g.dispose();

		// Soften.
		float softenFactor = 0.05f;
		float[] softenArray = { 0, softenFactor, 0, softenFactor,
				1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0 };
		Kernel kernel = new Kernel(3, 3, softenArray);
		ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
		bufferedImage = cOp.filter(bufferedImage, null);
		
		String formatName = originalFile.getName().substring(originalFile.getName().lastIndexOf(".") + 1);
		ImageIO.write(bufferedImage, formatName, resizedFile);
		

//		// Write the jpeg to a file.
//		FileOutputStream out = new FileOutputStream(resizedFile);
//
//		// Encodes image as a JPEG data stream
//		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//
//		JPEGEncodeParam param = encoder
//				.getDefaultJPEGEncodeParam(bufferedImage);
//
//		param.setQuality(quality, true);
//
//		encoder.setJPEGEncodeParam(param);
//		encoder.encode(bufferedImage);
	}
	
	/**
	 * 获取上传图片的颜色
	 * @param imagePath 图片路径
	 * @return
	 */
	public static String getImgRGB(String imagePath)
	{
		String[] h=new String[256];
		   h[0]="00";h[1]="01";h[2]="02";h[3]="03";h[4]="04";h[5]="05";
		   h[6]="06";h[7]="07";h[8]="08";h[9]="09";h[10]="0A";h[11]="0B";
		   h[12]="0C";h[13]="0D";h[14]="0E";h[15]="0F";h[16]="10";h[17]="11";
		   h[18]="12";h[19]="13";h[20]="14";h[21]="15";h[22]="16";h[23]="17";
		   h[24]="18";h[25]="19";h[26]="1A";h[27]="1B";h[28]="1C";h[29]="1D";
		   h[30]="1E";h[31]="1F";h[32]="20";h[33]="21";h[34]="22";h[35]="23";
		   h[36]="24";h[37]="25";h[38]="26";h[39]="27";h[40]="28";h[41]="29";
		   h[42]="2A";h[43]="2B";h[44]="2C";h[45]="2D";h[46]="2E";h[47]="2F";
		   h[48]="30";h[49]="31";h[50]="32";h[51]="33";h[52]="34";h[53]="35";
		   h[54]="36";h[55]="37";h[56]="38";h[57]="39";h[58]="3A";h[59]="3B";
		   h[60]="3C";h[61]="3D";h[62]="3E";h[63]="3F";h[64]="40";h[65]="41";
		   h[66]="42";h[67]="43";h[68]="44";h[69]="45";h[70]="46";h[71]="47";
		   h[72]="48";h[73]="49";h[74]="4A";h[75]="4B";h[76]="4C";h[77]="4D";
		   h[78]="4E";h[79]="4F";h[80]="50";h[81]="51";h[82]="52";h[83]="53";
		   h[84]="54";h[85]="55";h[86]="56";h[87]="57";h[88]="58";h[89]="59";
		   h[90]="5A";h[91]="5B";h[92]="5C";h[93]="5D";h[94]="5E";h[95]="6F";
		   h[96]="60";h[97]="61";h[98]="62";h[99]="63";h[100]="64";h[101]="65";
		   h[102]="66";h[103]="67";h[104]="68";h[105]="69";h[106]="6A";h[107]="6B";
		   h[108]="6C";h[109]="6D";h[110]="6E";h[111]="6F";h[112]="70";h[113]="71";
		   h[114]="72";h[115]="73";h[116]="74";h[117]="75";h[118]="76";h[119]="77";
		   h[120]="78";h[121]="79";h[122]="7A";h[123]="7B";h[124]="7C";h[125]="7D";
		   h[126]="7E";h[127]="7F";h[128]="80";h[129]="81";h[130]="82";h[131]="83";
		   h[132]="84";h[133]="85";h[134]="86";h[135]="87";h[136]="88";h[137]="89";
		   h[138]="8A";h[139]="8B";h[140]="8C";h[141]="8D";h[142]="8E";h[143]="8F";
		   h[144]="90";h[145]="91";h[146]="92";h[147]="93";h[148]="94";h[149]="95";
		   h[150]="96";h[151]="97";h[152]="98";h[153]="99";h[154]="9A";h[155]="9B";
		   h[156]="9C";h[157]="9D";h[158]="9E";h[159]="9F";h[160]="A0";h[161]="A1";
		   h[162]="A2";h[163]="A3";h[164]="A4";h[165]="A5";h[166]="A6";h[167]="A7";
		   h[168]="A8";h[169]="A9";h[170]="AA";h[171]="AB";h[172]="AC";h[173]="AD";
		   h[174]="AE";h[175]="AF";h[176]="B0";h[177]="B1";h[178]="B2";h[179]="B3";
		   h[180]="B4";h[181]="B5";h[182]="B6";h[183]="B7";h[184]="B8";h[185]="B9";
		   h[186]="BA";h[187]="BB";h[188]="BC";h[189]="BD";h[190]="BE";h[191]="BF";
		   h[192]="C0";h[193]="C1";h[194]="C2";h[195]="C3";h[196]="C4";h[197]="C5";
		   h[198]="C6";h[199]="C7";h[200]="C8";h[201]="C9";h[202]="CA";h[203]="CB";
		   h[204]="CC";h[205]="CD";h[206]="CE";h[207]="CF";h[208]="D0";h[209]="D1";
		   h[210]="D2";h[211]="D3";h[212]="D4";h[213]="D5";h[214]="D6";h[215]="D7";
		   h[216]="D8";h[217]="D9";h[218]="DA";h[219]="DB";h[220]="DC";h[221]="DD";
		   h[222]="DE";h[223]="DF";h[224]="E0";h[225]="E1";h[226]="E2";h[227]="E3";
		   h[228]="E4";h[229]="E5";h[230]="E6";h[231]="E7";h[232]="E8";h[233]="E9";
		   h[234]="EA";h[235]="EB";h[236]="EC";h[237]="ED";h[238]="EE";h[239]="EF";
		   h[240]="F0";h[241]="F1";h[242]="F2";h[243]="F3";h[244]="F4";h[245]="F5";
		   h[246]="F6";h[247]="F7";h[248]="F8";h[249]="F9";h[250]="FA";h[251]="FB";
		   h[252]="FC";h[253]="FD";h[254]="FE";h[255]="FF";
		int[] rgb = new int[3];

		File file = new File(imagePath);
		BufferedImage bi = null;
		try
		{
			bi = ImageIO.read(file);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		int width = bi.getWidth();
		int height = bi.getHeight();
		int minx = bi.getMinX();
		int miny = bi.getMinY();

		int pixel = bi.getRGB(1, 1);
		rgb[0] = (pixel & 0xff0000) >> 16;
		rgb[1] = (pixel & 0xff00) >> 8;
		rgb[2] = (pixel & 0xff);
		//此值存数据库
		String B16 = "#" + h[rgb[0]] + h[rgb[1]] + h[rgb[2]];
		return B16;

	}
	
	
}
