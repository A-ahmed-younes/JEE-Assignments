import threading
import socket
import logging

logging.basicConfig(level=logging.DEBUG)

def client_receive(client):
    while True:
        try:
            message = client.recv(1024).decode('utf-8').strip()
            if message:
                print(message)
        except Exception as e:
            logging.error('Error in client_receive: %s', str(e))
            break

def client_send(client):
    while True:
        msg = input()
        if msg.lower() in ['exit', 'quit']:
            break
        client.sendall((msg + '\n').encode('utf-8'))

def main():
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as client:
        client.connect(('localhost', 1234))

        receive_thread = threading.Thread(target=client_receive, args=(client,))
        send_thread = threading.Thread(target=client_send, args=(client,))

        receive_thread.start()
        send_thread.start()

        receive_thread.join()
        send_thread.join()

# Check if the file is being run directly and not imported as a module
if __name__ == "__main__":
    main()