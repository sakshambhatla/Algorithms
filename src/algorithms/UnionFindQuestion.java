/*
 * https://github.com/tasti/Algorithms-Part-I/blob/master/job-interview-questions/union-find.md
 * 
 * Social network connectivity. Given a social network containing N members and a log file 
 * containing M timestamps at which times pairs of members formed friendships, design an algorithm 
 * to determine the earliest time at which all members are connected (i.e., every member is a 
 * friend of a friend of a friend ... of a friend). Assume that the log file is sorted by timestamp 
 * and that friendship is an equivalence relation. The running time of your algorithm should be 
 * MlogN or better and use extra space proportional to N.
 * 
 * Example, a-b:1,  a-c:3,  d-e:4, c-d:10, a-d:15
 * Ans: 10 
 * 
 * Lessons learnt-
 * Union-Find will always need a data structure, and the best one seems to simply an int array,
 * where index will correspond to friend ID, and value will be the group ID.
 * 
 * We would need a separate data structure to store relationship between friend name and friend ID.
 * We could use a hash map for that, but note that we will only have a 1 way relationship - We will
 * be able to get friend ID (acts as index) from friend name, but not vice versa. This should be
 * sufficient for most cases.
 * 
 * ArrayList works great to store log entries. Each log entry is a list member with friend A, friend B
 * and timestamp.
 */

package algorithms;

import java.util.ArrayList;
import java.util.HashMap;

public class UnionFindQuestion {

	public static void main(String[] args) throws InterruptedException {
		Friendship f = new Friendship();
		System.out.println("First complete relationship occured at " + f.getEarliestCompleteNetwork());
	}

}

class FriendConnection {
	int a;
	int b;
	long timestamp;
}

class FriendshipLog {
	ArrayList<FriendConnection> flogList;
	
	FriendshipLog() {
		flogList = new ArrayList<FriendConnection>();
	}
	
	void addFriendship (int f1, int f2) {
		FriendConnection connection = new FriendConnection();
		connection.a = f1;
		connection.b = f2;
		long timestamp = System.currentTimeMillis() / 100;
		connection.timestamp = timestamp;
		flogList.add(connection);
		System.out.println("Added " + f1 + " and " + f2 + " at time " + timestamp);
	}
	
	FriendConnection getFriendConnectionbyIndex (int index) {
		return (flogList.get(index));
	}
	
	int logSize () {
		return (flogList.size());
	}
	
}

class Friendship {
	FriendshipLog flog = new FriendshipLog();
	int m;
	
	Friendship() throws InterruptedException {
		flog.addFriendship(10, 20);
		Thread.sleep(500);
		flog.addFriendship(20, 30);
		Thread.sleep(500);
		flog.addFriendship(30, 40);
		Thread.sleep(500);
		flog.addFriendship(10, 40);
		Thread.sleep(500);
		flog.addFriendship(10, 50);
		Thread.sleep(500);
		flog.addFriendship(20, 50);
		m = flog.logSize();
		//System.out.println("m is " + m);
	}
	
	long getEarliestCompleteNetwork() {
		int j=0;
		long tstamp=0;
		HashMap <Integer, Integer> friendID = new HashMap<Integer, Integer>();
		
		/*
		 * Populate HashMap with Friend:group ID. This mapping will allow us to 
		 * retrieve group ID associated with the friend.
		 * Initially, they all belong to unique groups. ID's actual value doesn't matter.
		 */
		for (int i=0; i<m; i++) {
			FriendConnection fc = flog.getFriendConnectionbyIndex(i);
			//System.out.println("Timestamp " + fc.timestamp);
			if (!friendID.containsKey(fc.a)) {
				friendID.put(fc.a, j);
				j++;
			}
			
			if (!friendID.containsKey(fc.b)) {
				friendID.put(fc.b, j);
				j++;
			}
		}
	
		//System.out.println("union size is " + union.length);
		
		regularUnionFind regUnion = new regularUnionFind(friendID.size());
		
		/*
		 * For every entry in log, do a union. Then check if union is complete.
		 * Time complexity: O(m)*max(O(union), O(find_all)) = O(mn)
		 */
		for (int i=0; i<m; i++) {
			FriendConnection fc = flog.getFriendConnectionbyIndex(i);
			//System.out.print("a's index is " + friendID.get(fc.a) + " and b's index is " + friendID.get(fc.b) + "; ");
			//System.out.println("a's group is " + union[friendID.get(fc.a)] + " and b's group is " + union[friendID.get(fc.b)]);
			
			regUnion.union(friendID.get(fc.a), friendID.get(fc.b));
			
			if (regUnion.connectedComponents()) {
				tstamp = fc.timestamp;
				break;
			}
		}

		return tstamp;
	}
}


