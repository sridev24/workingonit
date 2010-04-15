/*
 * Copyright 2008-2009 Vladimir Ritz Bossicard
 *
 * This file is part of WorkingOnIt.
 *
 * WorkingOnIt is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by the Free 
 * Software Foundation, either version 3 of the License, or (at your option) 
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 *
 * Version     : $Revision: 352 $
 * Last edit   : $Date: 2010-03-05 17:33:45 +0100 (Fri, 05 Mar 2010) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.clusterus.singleton.jgroups;

import java.util.Collection;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jgroups.Address;
import org.jgroups.Channel;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.Receiver;
import org.jgroups.View;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.workingonit.clusterus.singleton.AbstractCluster;
import org.workingonit.clusterus.singleton.Cluster;
import org.workingonit.clusterus.singleton.Node;

/**
 * Implementation of the {@link Cluster} interface using the 
 * <a href="http://jgroups.org">JGroups</a> Open Source library.
 * 
 * @author Vladimir Ritz Bossicard
 */
public class JGroupsCluster extends AbstractCluster implements Receiver, InitializingBean, DisposableBean {

    private static final Log log = LogFactory.getLog(JGroupsCluster.class);

    private JChannel channel;
    protected Map<Object, Node> nodes = new ConcurrentHashMap<Object, Node>();
    private String groupName = "SINGULUS";

    public void setChannel(final JChannel channel) {
        this.channel = channel;
    }

    public void setGroupName(final String groupName) {
        this.groupName = groupName;
    }

    public Collection<Node> getNodes() {
        return this.nodes.values();
    }

    public void start() throws Exception {
        this.channel.connect(this.groupName);
        this.nodes.put(this.channel.getLocalAddress(), new Node(this.channel.getLocalAddress().toString()));
    }

    public void afterPropertiesSet() throws Exception {
        // this.channel.setOpt(Channel.AUTO_RECONNECT, Boolean.TRUE);
        this.channel.setReceiver(this);
        // this.channel.connect(this.groupName);
        this.channel.setOpt(Channel.AUTO_RECONNECT, Boolean.TRUE);
    }

    public void destroy() throws Exception {
        this.channel.disconnect();
    }

    public Node getLocalNode() {
        return this.nodes.get(this.channel.getLocalAddress());
    }

    public void suspect(final Address address) {
        if (log.isInfoEnabled()) {
            log.info("suspected address: " + address);
        }
    }

    public void viewAccepted(final View view) {
        if (log.isDebugEnabled()) {
            log.debug("view accepted " + view);
        }
        
        // check if new members have been defined in the view and add them if 
        // they are not already present..
        Vector<Address> members = view.getMembers();
        for (Address ip : members) {
            if (!this.nodes.containsKey(ip)) {
                Node node = new Node(ip.toString());
                if (log.isDebugEnabled()) {
                    log.debug("adding node [" + node.toString() + "]");
                }
                this.nodes.put(ip, node);
            }
        }
        // check if all members are still in the view and remove them if they
        // are not present anymore
        for (Object element : this.nodes.keySet()) {
            Address ip = (Address) element;
            if (!members.contains(ip)) {
                if (log.isDebugEnabled()) {
                    log.debug("removing node [" + ip.toString() + "]");
                }
                this.nodes.remove(ip);
            }
        }

        // process to the election
        doElection();
    }

    public byte[] getState() {
        return null;
    }

    public void receive(final Message msg) {
        // noop
    }

    public void setState(final byte[] state) {
        // noop
    }

    public void block() {
        // noop
    }

}
