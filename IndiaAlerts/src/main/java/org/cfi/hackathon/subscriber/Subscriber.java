package org.cfi.hackathon.subscriber;

import lombok.Data;

@Data
public class Subscriber 
{
	private String number;
	private Carrier carrier;
	
	public static enum Carrier {
		TMOBILE("tmomail.net"),
		VERIZON("vtext.com"),
		SPRINT("messaging.sprintpcs.com"),
		ATT("txt.att.net");
		
		private Carrier(String dom)
		{
			domain = dom;
		}
		
		private String domain;
		
		public String getDomain()
		{
			return domain;
		}
	}
	
	public String getAddress()
	{
		String num = Carrier.TMOBILE.equals(carrier) ? number : number.substring(1);
		return num + "@" + carrier.getDomain();
	}
	
	public static Carrier getCarrier(String carrier)
	{
		switch(carrier)
		{
			case "TMOBILE":
				return Carrier.TMOBILE;
			case "VERIZON":
				return Carrier.VERIZON;
			case "SPRINT":
				return Carrier.SPRINT;
			case "ATT":
				return Carrier.ATT;
		}
		return null;
	}
}
