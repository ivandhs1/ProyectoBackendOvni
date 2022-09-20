package com.grupo2.springboot.backend.apirest.auth;

public class JwtConfig {
	public static final String LLAVE_SECRETA = "vinos.ovni.vinos";
	
	public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\r\n"
			+ "MIIEpAIBAAKCAQEAxnHwS9YcIroFbGgZnA04OOhCZwCVehoxydIAkh4ggYYRBcA9\r\n"
			+ "4jYrtY4L/Hfyau3d/+WW2LL+5GgOhxaTwlKfX36cRe+2cmHvDZJqG8o3LKdHjDhx\r\n"
			+ "zLIyNcqhuTAS7SgO/W4dPzz8/nQY2v6GM5ZLjEJ4OEJgqoHouB2yJeCJnThq+x3p\r\n"
			+ "Wg+dD20NADIuGi92ItQkYLGPSgY9oY9H7fl9DIyTnQL+XLpIDdSNaXfS58Xi/Eg3\r\n"
			+ "HgB/EZTAuTEXVwNSKLuy6ocsnSe5gUgQODnk/8eRnWBBLBHd8NPwrKjBDodlc2my\r\n"
			+ "gd6M+kDkGYhbYmxLCPSRsFpfjK9R2LBk8h+enQIDAQABAoIBAQCY0P0fKU6ZX271\r\n"
			+ "wGSYS4XULOeVsllpoepdZJ6T5ydZFwjcyOHe5aBbO3v3e9buiSa//OlhDtZCxe8K\r\n"
			+ "EFTQ/BdGzpVBNgvloY0Zgnq708kRVfA/Buh7nW4VBmnr1h19C7AfcUCMhka9HGJX\r\n"
			+ "olFuQHsFE9rIWDVgmJHB1vxtNrBCpOhd6fu8Mo2h40UljYkaUavZrCmdtqih0mSn\r\n"
			+ "ECaiqYXcvuwkk2S/zkwuOL41cqSr3hyHCTWgp2TjbqdXPANq7zXZWg+xFgREyfZl\r\n"
			+ "S0d2eYNl54oHO36Kf4/+eLIYEV2KwUrxOWqbhb6JnOEHH7pDTsh/hDB/jVhq1sO0\r\n"
			+ "Uh8jAO2BAoGBAOcMUcfUbqo8TnJjb/bGUWUVTLSaEeGrShDMfuDtxpYOGv5YNI5g\r\n"
			+ "U/G6AjIGlBmyGZ0BHutuYUiyhTTFkvCpQ4L9W236Vax50j5SLQkZ8mLL1Ip6OZa1\r\n"
			+ "lzjbq2xHgtvwikZknn2jCm0FRxxqbCOZW1Kk/s2FfRX/2/pVQ8llpI/ZAoGBANvg\r\n"
			+ "QfzYxA+3LO8D0ZUhIM9bXJuc/LJDmNE3FNZWp3Im8E95Mp+HmDXXImtiUS1/66hX\r\n"
			+ "c7ySEwponqN6gnERR3HKp1cU+HqA4VMYNbnIq6almXfA5hLTLH6+joM2464mk86i\r\n"
			+ "qoUiG9b/PtWFl8cJzoA9wBQhyQ7pK31HIhW63Q5lAoGBAKggLc5Uhaa4tXjFZO7U\r\n"
			+ "htwgcK2mw0ZMh1vMArOYMn50iy3zb2L/M3ZdnUVbxh8gm0TFUQOOUZo91XJ+Jpua\r\n"
			+ "/7ZsuEBB/l/AUb3pvD24NMmxetq9b/R79St5pbboHo/+7A+nHjFcF7UXNxS1WDVb\r\n"
			+ "Xbm4uHVDIq+EfEUgEHCX1vSZAoGAYYeR/aPxI79usP6SooG1WgT+iRATjscpK/Qw\r\n"
			+ "y/pX/+yBv97Us6qju44X+GX9+B6720ofTk/FHmLVq1IWNi0h8bmgjKICreQpDoC4\r\n"
			+ "pBX8/ciK0HHCHCuLmxTPhih8yazW91t0I+XDT5ScvH2rm2AgNwYtA0ERPAn1fnZl\r\n"
			+ "Chp/H3kCgYBV+PgpDojGJ9DAj2QyLr39yl8Pq/xDJaZPgxT3Vx3Gur9P3e4M76Wt\r\n"
			+ "/FaQTGAJqanYgrX0BAyeS6B/T6fdH5desZZwdKL8cbGE2pRw8kQp2Io08llHD7K1\r\n"
			+ "IhZx407vG7NBl3o+tcSN9UEFC/+8K4ovh5De94ePNKNW/v2t/ggpCg==\r\n"
			+ "-----END RSA PRIVATE KEY-----";

	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxnHwS9YcIroFbGgZnA04\r\n"
			+ "OOhCZwCVehoxydIAkh4ggYYRBcA94jYrtY4L/Hfyau3d/+WW2LL+5GgOhxaTwlKf\r\n"
			+ "X36cRe+2cmHvDZJqG8o3LKdHjDhxzLIyNcqhuTAS7SgO/W4dPzz8/nQY2v6GM5ZL\r\n"
			+ "jEJ4OEJgqoHouB2yJeCJnThq+x3pWg+dD20NADIuGi92ItQkYLGPSgY9oY9H7fl9\r\n"
			+ "DIyTnQL+XLpIDdSNaXfS58Xi/Eg3HgB/EZTAuTEXVwNSKLuy6ocsnSe5gUgQODnk\r\n"
			+ "/8eRnWBBLBHd8NPwrKjBDodlc2mygd6M+kDkGYhbYmxLCPSRsFpfjK9R2LBk8h+e\r\n"
			+ "nQIDAQAB\r\n"
			+ "-----END PUBLIC KEY-----";

}
