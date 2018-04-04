package c15.net
// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel
import jcsp.lang.*
import groovyJCSP.*
import jcsp.net2.*
import jcsp.net2.tcpip.*
import jcsp.userIO.*

def numberOfGets = Ask.Int("How many get processes (2..9)?", 2, 9)

def manyGetNodeIP = "127.0.0.2"
def manyGetAddr = new TCPIPNodeAddress(manyGetNodeIP, 3000)
Node.getInstance().init (manyGetAddr)

def comms = NetChannel.net2any()
def pList = (0 ..< numberOfGets).collect{
	i -> new Get ( inChannel: comms, id: i ) 
	}

new PAR ( pList ).run()
