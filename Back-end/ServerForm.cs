using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Text.RegularExpressions;
using System.Windows.Forms;

namespace GameSocket
{

    public partial class Form1 : Form
    {
        private byte[] _buffer = new byte[1024];
        public List<SocketT2h> __ClientSockets { get; set; }
        List<string> _names = new List<string>();
        private Socket _serverSocket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
        public Form1()
        {
            
                InitializeComponent();
                CheckForIllegalCrossThreadCalls = false;
                __ClientSockets = new List<SocketT2h>();
            SetupServer();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            SetupServer();
        }
        private void SetupServer()
        {
            lb_details.Text = "Setting up server . . .";
            _serverSocket.Bind(new IPEndPoint(IPAddress.Any, 100));
            _serverSocket.Listen(1);
            _serverSocket.BeginAccept(new AsyncCallback(AppceptCallback), null);
            lb_details.Text = "Server up";

        }
        private void AppceptCallback(IAsyncResult ar)
        {
            Socket socket = _serverSocket.EndAccept(ar);
            __ClientSockets.Add(new SocketT2h(socket));
            list_Client.Items.Add(socket.RemoteEndPoint.ToString());

            lb_general.Text = "Clients connected: " + __ClientSockets.Count.ToString();
            lb_details.Text = "Client connected. . .";
            socket.BeginReceive(_buffer, 0, _buffer.Length, SocketFlags.None, new AsyncCallback(ReceiveCallback), socket);
            _serverSocket.BeginAccept(new AsyncCallback(AppceptCallback), null);

            //add data here to send when android connects
            Sendata(socket, "connect data");
        }

        private void ReceiveCallback(IAsyncResult ar)
        {

            Socket socket = (Socket)ar.AsyncState;
            if (socket.Connected)
            {
                int received;
                try
                {
                    received = socket.EndReceive(ar);
                }
                catch (Exception)
                {
                 
                    for (int i = 0; i < __ClientSockets.Count; i++)
                    {
                        if (__ClientSockets[i]._Socket.RemoteEndPoint.ToString().Equals(socket.RemoteEndPoint.ToString()))
                        {
                            __ClientSockets.RemoveAt(i);
                            list_Client.Items.Remove(socket.RemoteEndPoint.ToString());
                            lb_general.Text = "Clients connected: " + __ClientSockets.Count;
                          
                        }
                    }

                    return;
                }
                if (received != 0)
                {
                    byte[] dataBuf = new byte[received];
                    Array.Copy(_buffer, dataBuf, received);
                    string text = Encoding.ASCII.GetString(dataBuf);


                    string value = text;
                    string[] lines = Regex.Split(value, "\r\n");

                    foreach (string line in lines)
                    {
                        if (line != "")
                        {
                            text = line;
                        }
                        else if (line == "")
                        {
                            break;
                        }


     


                        string reponse = string.Empty;

                        for (int i = 0; i < __ClientSockets.Count; i++)
                        {
                            if (socket.RemoteEndPoint.ToString().Equals(__ClientSockets[i]._Socket.RemoteEndPoint.ToString()))
                            {
                                rich_Text.AppendText("\n" + __ClientSockets[i]._Name + ": " + text);
                            }
                        }






                    }
                }
                else
                {
                    for (int i = 0; i < __ClientSockets.Count; i++)
                    {
                        if (__ClientSockets[i]._Socket.RemoteEndPoint.ToString().Equals(socket.RemoteEndPoint.ToString()))
                        {
                            __ClientSockets.RemoveAt(i);
                            list_Client.Items.Remove(socket.RemoteEndPoint.ToString());
                            lb_general.Text = "Clients connected: " + __ClientSockets.Count.ToString();
                        }
                    }
                }
            }
            socket.BeginReceive(_buffer, 0, _buffer.Length, SocketFlags.None, new AsyncCallback(ReceiveCallback), socket);
        }
        void Sendata(Socket socket, string noidung)
        {
            byte[] data = Encoding.ASCII.GetBytes(noidung);
            socket.BeginSend(data, 0, data.Length, SocketFlags.None, new AsyncCallback(SendCallback), socket);
            _serverSocket.BeginAccept(new AsyncCallback(AppceptCallback), null);
        }
        private void SendCallback(IAsyncResult AR)
        {
            Socket socket = (Socket)AR.AsyncState;
            socket.EndSend(AR);
        }

        private void btnSend_Click(object sender, EventArgs e)
        {
            for (int i = 0; i < list_Client.SelectedItems.Count; i++)
            {
                string t = list_Client.SelectedItems[i].ToString();
                for (int j = 0; j < __ClientSockets.Count; j++)
                {
         
                    {
                        Sendata(__ClientSockets[j]._Socket, txt_Text.Text);
                    }
                }
            }
            rich_Text.AppendText("\nServer: " + txt_Text.Text);
        }

        private void statusStrip1_ItemClicked(object sender, ToolStripItemClickedEventArgs e)
        {

        }
    }
}
