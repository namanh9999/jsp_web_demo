package model;

public class EnumClass {
	public enum OrderStatus {
		Pending, Confirmed, Shipped, Delivered
	}

	public enum Payments {
		Credit_card, Debit_card, Paypal, Google_pay
	}

	public enum PaymentStatus {
		Pending_Payment, Successful, Failed,
	}

	public enum Language {
		English, VietNamese
	}

	public static OrderStatus getOrderStatus(String value) {
		if (value.equals("Pe")) {
			return EnumClass.OrderStatus.Pending;
		} else if (value.equals("Con")) {
			return EnumClass.OrderStatus.Confirmed;
		} else if (value.equals("Del")) {
			return EnumClass.OrderStatus.Shipped;
		}
		return EnumClass.OrderStatus.Delivered;
	}

	public static PaymentStatus getPaymentStatus(String value) {
		if (value.equals("Fai")) {
			return EnumClass.PaymentStatus.Failed;
		} else if (value.equals("Suc")) {
			return EnumClass.PaymentStatus.Successful;
		}
		return EnumClass.PaymentStatus.Pending_Payment;
	}

	public static Payments getPayments(String value) {
		if (value.equals("Cre")) {
			return EnumClass.Payments.Credit_card;
		} else if (value.equals("Deb")) {
			return EnumClass.Payments.Debit_card;
		} else if (value.equals("Goog")) {
			return EnumClass.Payments.Google_pay;
		}
		return EnumClass.Payments.Paypal;
	}

	public static Language getLanguage(String value) {
		if (value.equals("VN")) {
			return EnumClass.Language.VietNamese;
		}
		return EnumClass.Language.English;
	}

	public static String StringHanding(String value) {
		if (value.equals("Vietname")) {
			return "VN";
		} else if (value.equals("EngLish")) {
			return "EN";
		} else if (value.equals("Confirmed")) {
			return "Con";
		} else if (value.equals("Delivered")) {
			return "Del";
		} else if (value.equals("Pending")) {
			return "Pe";
		} else if (value.equals("Shipped")) {
			return "Sh";
		} else if (value.equals("Failed")) {
			return "Fai";
		} else if (value.equals("Pending_Payment")) {
			return "Pen";
		} else if (value.equals("Successful")) {
			return "Suc";
		} else if (value.equals("Credit_card")) {
			return "Cre";
		} else if (value.equals("Debit_card")) {
			return "Deb";
		} else if (value.equals("Google_Pay")) {
			return "Goog";
		}
		return "Pay";
	}

}
