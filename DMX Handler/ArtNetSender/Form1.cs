using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Net;

namespace ArtNetSender
{
    public partial class Form1 : Form
    {
        byte[] _dmxData;
        ArtNet.Sockets.ArtNetSocket artnet;

        public Form1()
        {
            InitializeComponent();
            artnet = new ArtNet.Sockets.ArtNetSocket();
        }

        private void trackBar1_Scroll(object sender, EventArgs e)
        {
            label1.Text = trackBar1.Value.ToString();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (artnet == null || !artnet.PortOpen)
                {
                    artnet.EnableBroadcast = true;
                    artnet.Open(IPAddress.Parse("127.0.0.1"), IPAddress.Parse("255.255.255.255"));
                    Console.WriteLine(artnet.BroadcastAddress.ToString());
                    Console.WriteLine("opened UDP connection...");
                    _dmxData = new byte[512];
                }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            if (_dmxData != null && artnet != null)
            {
                _dmxData[(int)numericUpDown1.Value] = Convert.ToByte(trackBar1.Value);
                var packet = new ArtNet.Packets.ArtNetDmxPacket();
                packet.DmxData = _dmxData;
                artnet.Send(packet);
            }
            else
            {
                Console.WriteLine("uninitialized properties");
            }
        }

        private void button3_Click(object sender, EventArgs e)
        {
            artnet.
        }
    }
}
