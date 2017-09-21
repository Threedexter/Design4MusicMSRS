﻿using System;
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
            label2.Text = "NOT CONNECTED";
            label2.ForeColor = Color.Red;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (artnet == null || !artnet.PortOpen)
            {
                artnet.EnableBroadcast = true;
                if (Localcheckbox.Checked)
                { 
                    try
                    {
                        artnet.Open(IPAddress.Parse("192.168.56.1"), IPAddress.Parse("255.255.255.0"));
                    }
                    catch (Exception)
                    {
                        try
                        {
                            artnet.Open(IPAddress.Parse("127.0.0.1"), IPAddress.Parse("255.255.255.0"));
                        }
                        catch(Exception ex)
                        {
                            MessageBox.Show("Something went wrong, \n" + ex.Message);
                            return;
                        }
                    }
                    
                }
                else
                {
                    try
                    {
                        artnet.Open(IPAddress.Parse(IpTextBox.Text), IPAddress.Parse("255.255.255.0"));
                    }
                    catch (Exception ex)
                    {
                        MessageBox.Show("Something went wrong, \n" + ex.Message);
                        return;
                    }
                }
                //Console.WriteLine(artnet.BroadcastAddress.ToString());
                //Console.WriteLine("opened UDP connection...");
                _dmxData = new byte[512];
                label2.Text = "CONNECTED";
                label2.ForeColor = Color.Green;
                button1.Enabled = false;
            }
        }

        #region Trackbar form controls

        private void trackBar1_Scroll(object sender, EventArgs e)
        {
            label1.Text = trackBar1.Value.ToString();
            SendPacket((int)numericUpDown1.Value, Convert.ToByte(trackBar1.Value));
        }

        private void trackBar2_Scroll(object sender, EventArgs e)
        {
            label3.Text = trackBar2.Value.ToString();
            SendPacket((int)numericUpDown2.Value, Convert.ToByte(trackBar2.Value));
        }

        private void trackBar3_Scroll(object sender, EventArgs e)
        {
            label4.Text = trackBar3.Value.ToString();
            SendPacket((int)numericUpDown3.Value, Convert.ToByte(trackBar3.Value));
        }

        private void trackBar4_Scroll(object sender, EventArgs e)
        {
            label5.Text = trackBar4.Value.ToString();
            SendPacket((int)numericUpDown4.Value, Convert.ToByte(trackBar4.Value));
        }

        private void trackBar5_Scroll(object sender, EventArgs e)
        {
            label6.Text = trackBar5.Value.ToString();
            SendPacket((int)numericUpDown5.Value, Convert.ToByte(trackBar5.Value));
        }

        private void trackBar6_Scroll(object sender, EventArgs e)
        {
            label7.Text = trackBar6.Value.ToString();
            SendPacket((int)numericUpDown6.Value, Convert.ToByte(trackBar6.Value));
        }

        private void trackBar7_Scroll(object sender, EventArgs e)
        {
            label8.Text = trackBar7.Value.ToString();
            SendPacket((int)numericUpDown7.Value, Convert.ToByte(trackBar7.Value));
        }

        private void trackBar8_Scroll(object sender, EventArgs e)
        {
            label9.Text = trackBar8.Value.ToString();
            SendPacket((int)numericUpDown8.Value, Convert.ToByte(trackBar8.Value));
        }
        #endregion

        public void SendPacket(int channel, byte value)
        {
            if (_dmxData != null && artnet != null)
            {
                _dmxData[channel - 1] = value;
                ArtNet.Packets.ArtNetDmxPacket packet = new ArtNet.Packets.ArtNetDmxPacket();
                packet.Universe = 0;
                //Console.WriteLine("Packet protocol:" + packet.Protocol.ToString() + ", Packet Version:" + packet.Version + ", Datagram OpCode:" + packet.OpCode);
                packet.DmxData = _dmxData;
                artnet.Send(packet);
                //Console.WriteLine("sent data packet");
            }
            else
            {
                //Console.WriteLine("uninitialized properties");
            }
        }

        private void Localcheckbox_CheckedChanged(object sender, EventArgs e)
        {
            if (Localcheckbox.Checked)
            {
                IpTextBox.Enabled = false;
            }
        }
    }
}
