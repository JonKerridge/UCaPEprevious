package c17.test

import jcsp.lang.*
import groovyJCSP.*
import jcsp.net2.*
import jcsp.net2.cns.*
import jcsp.net2.tcpip.*

Node.info.setDevice(null)

Node.getInstance().init(new TCPIPNodeFactory ())
  
NetChannelInput ordinaryInput = CNS.createNet2One("ordinaryInput")
NetChannelOutput scaledOutput = CNS.createOne2Net("scaledOutput")

new PAR(new ScalingDevice (inChannel: ordinaryInput, outChannel: scaledOutput) ).run()
 