package dht;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

/**
 * Remote interface to let Nodes interact with each other.
 * @author Andreas
 *
 */
public interface Node<V> extends Remote {
	
	/**
	 * Get the key associated with the Node.
	 * @return a key...
	 * @throws RemoteException
	 */
	public String getKey() throws RemoteException;
	
	/**
	 * Get the successor of this node. If there are no other nodes, a node's successor will be the node itself.
	 * @return The successor node.
	 * @throws RemoteException
	 */
	public Node<V> getSuccessor() throws RemoteException;
	
	/**
	 * Get the predecessor of this node. If there are no other nodes, a node's predecessor will be the node itself.
	 * @return The predecessor node.
	 * @throws RemoteException
	 */
	public Node<V> getPredecessor() throws RemoteException;
	
	/**
	 * Set this node's successor to succ.
	 * @param succ
	 * @throws RemoteException
	 */
	public void setSuccessor(Node<V> succ) throws RemoteException;
	
	/**
	 * Set this node's predecessor to pred.
	 * @param pred
	 * @throws RemoteException
	 */
	public void setPredecessor(Node<V> pred) throws RemoteException;
	
	/**
	 * Send (or forward) a probe through the network. A probe will simply print its' name to the standard output and forward it to its' successor.
	 * @param key - Key of the node who originally sent the probe.
	 * @param count - To get information about the number of nodes (incremented each hop).
	 * @throws RemoteException
	 */
	public void probe(String key, int count) throws RemoteException;
	
	/**
	 * Search the network for a Node responsible for this key. Used in put/get/remove.
	 * @param key
	 * @return a reference to the (remote) Node.
	 * @throws RemoteException
	 */
	public Node<V> lookup(String key) throws RemoteException;
	
	/**
	 * A network-level get.
	 * @param key
	 * @return the item paired with the given key.
	 * @throws RemoteException
	 */
	public V getStored(String key) throws RemoteException;
	
	/**
	 * A network-level put.
	 * @param key
	 * @param value
	 * @throws RemoteException
	 */
	public void addStored(String key, V value) throws RemoteException;
	
	/**
	 * A network-level remove.
	 * @param key
	 * @throws RemoteException
	 */
	public void removeStored(String key) throws RemoteException;
	
	/**
	 * Ask this node to hand over a part of its' storage to its' new predecessor.
	 * @param key
	 * @return
	 * @throws RemoteException
	 */
	public Map<String, V> handover(String oldPredKey, String newPredKey) throws RemoteException;
	
	/**
	 * @return all values contained in this node's storage.
	 * @throws RemoteException
	 */
//	public Collection<V> getValues() throws RemoteException;
	public List<V> getValues() throws RemoteException;
	
	/**
	 * Update the routing table of this node, given a list of all nodes in the network.
	 * @param nodes
	 * @throws RemoteException
	 */
	public void updateFingers(List<Node<V>> nodes) throws RemoteException;
}
