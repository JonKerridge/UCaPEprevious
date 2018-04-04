package c18.net
// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel
import jcsp.lang.*
import jcsp.net2.*
import jcsp.net2.tcpip.*
import groovyJCSP.*
import jcsp.userIO.*

def rootIP = "127.0.0.1"
def rootAddress = new TCPIPNodeAddress(rootIP, 3000)
Node.getInstance().init(rootAddress)
def fromNodes = NetChannel.net2one()

def String initialValue = Ask.string ( "Initial List Value ? ")
def int nodes = Ask.Int ("Number of nodes (1..8) ? ", 1, 8)

def rootNode = new TripRoot ( fromNodes: fromNodes, 
                               nodes: nodes,
                               initialValue: initialValue )

new PAR ( [rootNode] ).run()
