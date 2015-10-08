package com.common.mail;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.common.StringUtil;
import com.jfinal.kit.PropKit;

/**
 * 
 * @author
 * 
 */
public class MailUtils {

	public static String TEXT = "text/plain;charset=UTF-8";
	public static String HTML = "text/html;charset=UTF-8";
	private String host; //
	private boolean needAuth; //
	private static MailUtils mailUtils;

	public static synchronized MailUtils instance() {
		if (mailUtils == null)
			mailUtils = new MailUtils();
		return mailUtils;
	}

	/**
	 * 
	 */
	public MailUtils() {
		host = PropKit.get("email.server.host");
		needAuth = true;
	}

	/**
	 * 
	 * 
	 * @param needAuth
	 * 
	 */
	public MailUtils(boolean needAuth) {
		host = PropKit.get("email.server.host");
		this.needAuth = needAuth;
	}

	/**
	 *
	 * 
	 * @param host
	 * 
	 * @param needAuth
	 * 
	 */
	public MailUtils(String host, boolean needAuth) {
		this.host = host;
		this.needAuth = needAuth;
	}

	/**
	 * 
	 * 
	 * @param user
	 * @param pwd
	 * @return
	 */
	private Session getSession(String user, String pwd) {
		Properties props = new Properties();

		Session mailSession = null;
		props = java.lang.System.getProperties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.socketFactory.port",
				PropKit.get("email.server.port"));

		if (needAuth) {
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.auth", "true");
			MailAuthenticator myAuther = new MailAuthenticator(user, pwd);
			mailSession = Session.getInstance(props, (Authenticator) myAuther);
		} else {
			mailSession = Session.getInstance(props);
		}
		return mailSession;
	}

	/**
	 * 
	 * 
	 * @param mimeMsg
	 * 
	 * @param from
	 * 
	 * @param to
	 * 
	 * @throws MessagingException
	 */
	private void setFromAndTo(Message mimeMsg, String from, String to)
			throws MessagingException {
		if (StringUtil.notEmpty(from)) {
			InternetAddress sendFrom = new InternetAddress(from);
			mimeMsg.setFrom(sendFrom); //
		}
		System.out.println("send to:" + to);
		InternetAddress sendTo = new InternetAddress(to);
		mimeMsg.setRecipient(MimeMessage.RecipientType.TO, sendTo);
	}

	/**
	 * 
	 * 
	 * @param mimeMsg
	 * 
	 * @param from
	 * 
	 * @param tos
	 * 
	 * @throws MessagingException
	 */
	private void setFromAndTos(Message mimeMsg, String from, String[] tos)
			throws MessagingException {
		if (StringUtil.notEmpty(from)) {
			InternetAddress sendFrom = new InternetAddress(from);
			mimeMsg.setFrom(sendFrom); //
		}
		System.out.println("send to:" + tos);
		InternetAddress[] sendTo = null;
		if (tos != null && tos.length != 0) {
			sendTo = new InternetAddress[tos.length];
			for (int i = 0; i < tos.length; i++) {
				sendTo[i] = new InternetAddress(tos[i]);
			}
		}
		mimeMsg.setRecipients(MimeMessage.RecipientType.TO, sendTo);
	}

	/**
	 * 
	 * 
	 * @param mimeMsg
	 * 
	 * @param subject
	 * 
	 * @throws MessagingException
	 */
	private void setSubject(Message mimeMsg, String subject)
			throws MessagingException {
		mimeMsg.setSubject(subject);
	}

	/**
	 * 
	 * 
	 * @param text
	 * 
	 * @return MimeBodyPart
	 * @throws MessagingException
	 */
	private MimeBodyPart getBodyPart(String text) throws MessagingException {
		MimeBodyPart part = new MimeBodyPart();
		part.setContent(
				"<meta http-equiv=Content-Type content=text/html;charset=UTF-8>"
						+ text, "text/html;charset=UTF-8");
		return part;
	}

	/**
	 * 
	 * 
	 * @param file
	 * 
	 * @return MimeBodyPart
	 * @throws MessagingException
	 */
	private MimeBodyPart getFileAffixPart(String file)
			throws MessagingException {
		String fileName = "";
		String regex = "////([^////]*)$";
		Matcher m = Pattern.compile(regex).matcher(file);
		while (m.find()) {
			fileName = m.group(1);
		}
		MimeBodyPart part = new MimeBodyPart();
		FileDataSource fileds = new FileDataSource(file);
		part.setDataHandler(new DataHandler(fileds));
		try {
			part.setFileName(MimeUtility.encodeWord(fileName, "UTF-8", null));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return part;
	}

	/**
	 * 
	 * 
	 * @param userName
	 * 
	 * @param pwd
	 * 
	 * @param subject
	 * 
	 * @param from
	 * 
	 * @param to
	 * 
	 * @throws Exception
	 */
	public void send(String userName, String pwd, String subject, String from,
			String to, String text) throws Exception {
		Session mailSession = getSession(userName, pwd);
		MimeMessage mimeMsg = null;
		Transport transport = mailSession.getTransport("smtps");
		mimeMsg = new MimeMessage(mailSession);
		setFromAndTo(mimeMsg, from, to);
		setSubject(mimeMsg, subject);
		Multipart multipart = new MimeMultipart();
		MimeBodyPart mainPart = getBodyPart(text);
		multipart.addBodyPart(mainPart);
		mimeMsg.setContent(multipart);
		mimeMsg.setSentDate(new Date());
		mimeMsg.saveChanges();
		transport.connect(host, userName, pwd);
		transport.sendMessage(mimeMsg, mimeMsg.getAllRecipients());
		transport.close();
	}

	/**
	 */
	public void send(String to, String text, String subject) throws Exception {
		Session mailSession = getSession(PropKit.get("email.server.from"),
				PropKit.get("email.server.pwd"));
		MimeMessage mimeMsg = null; //
		Transport transport = mailSession.getTransport(PropKit
				.get("email.server.transport")); //
		mimeMsg = new MimeMessage(mailSession);
		setFromAndTo(mimeMsg, PropKit.get("email.server.from"), to);
		setSubject(mimeMsg, subject);
		Multipart multipart = new MimeMultipart();
		MimeBodyPart mainPart = getBodyPart(text);
		multipart.addBodyPart(mainPart);
		mimeMsg.setContent(multipart);
		mimeMsg.setSentDate(new Date());//
		mimeMsg.saveChanges();
		transport.connect(host, PropKit.get("email.server.from"),
				PropKit.get("email.server.pwd"));//
		transport.sendMessage(mimeMsg, mimeMsg.getAllRecipients());//
		transport.close();
	}

	/**
	 * send mail to many person
	 */
	public void send(String[] tos, String text, String subject)
			throws Exception {
		Session mailSession = getSession(PropKit.get("email.server.from"),
				PropKit.get("email.server.pwd"));
		MimeMessage mimeMsg = null; //
		Transport transport = mailSession.getTransport(PropKit
				.get("email.server.transport")); //
		mimeMsg = new MimeMessage(mailSession);
		setFromAndTos(mimeMsg, PropKit.get("email.server.from"), tos);
		setSubject(mimeMsg, subject);
		Multipart multipart = new MimeMultipart();
		MimeBodyPart mainPart = getBodyPart(text);
		multipart.addBodyPart(mainPart);
		mimeMsg.setContent(multipart);
		mimeMsg.setSentDate(new Date());//
		mimeMsg.saveChanges();
		transport.connect(host, PropKit.get("email.server.from"),
				PropKit.get("email.server.pwd"));//
		transport.sendMessage(mimeMsg, mimeMsg.getAllRecipients());//
		transport.close();
	}

	public void send(String userName, String pwd, String subject, String from,
			String to, String text, String[] filenames) throws Exception {
		Session mailSession = getSession(userName, pwd);
		MimeMessage mimeMsg = null; //
		Transport transport = mailSession.getTransport("smtps"); //
		mimeMsg = new MimeMessage(mailSession);
		setFromAndTo(mimeMsg, from, to);
		setSubject(mimeMsg, subject);
		Multipart multipart = new MimeMultipart();
		MimeBodyPart mainPart = getBodyPart(text);
		multipart.addBodyPart(mainPart);
		for (int i = 0; i < filenames.length; i++) {
			MimeBodyPart fileAffix = getFileAffixPart(filenames[i]);
			multipart.addBodyPart(fileAffix);
		}
		mimeMsg.setContent(multipart);
		mimeMsg.setSentDate(new Date());//
		mimeMsg.saveChanges();
		transport.connect(host, userName, pwd);//
		transport.sendMessage(mimeMsg, mimeMsg.getAllRecipients());//
		transport.close();
	}

}
