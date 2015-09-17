package com.common.server;
import java.net.MalformedURLException;

import org.apache.solr.client.solrj.impl.HttpSolrServer;

/**
 * SolrServer单例
 * @author zhenggm<a href="mailto:zhenggm@chsi.com.cn">zhenggm</a>
 * @version $Id$
 */
public class SolrServer {
    private static SolrServer solrServer = null;
    private static HttpSolrServer server=null;
    public static synchronized SolrServer getInstance() {
        if (solrServer==null){
           solrServer=new SolrServer();
        }
        return solrServer;
    }
    public static HttpSolrServer getServer(String url) throws MalformedURLException{
         if(server==null){
          server = new HttpSolrServer(url);
          server.setSoTimeout(1000);  // socket read timeout
          server.setConnectionTimeout(1000);
          server.setDefaultMaxConnectionsPerHost(100);
          server.setMaxTotalConnections(100);
          server.setFollowRedirects(false);  // defaults to false
          //allowCompression defaults to false.
          //Server side must support gzip or deflate for this to have any effect.
          server.setAllowCompression(true);
          server.setMaxRetries(1); // defaults to 0.  > 1 not recommended.
        }
        return server;
    }
}