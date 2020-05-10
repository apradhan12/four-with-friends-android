//package com.fourwithfriends.client;
//
//import com.fourwithfriends.dto.PlayerColor;
//
//public class ClientControllerImpl {
//
//    private ClientControllerDelegate controller;
//
//    public static void main(String[] args) {
//        new ClientControllerImpl().run();
//    }
//
//    private ClientControllerImpl() {
//        IClientModel model = new ClientModel();
//        IClientView view = new ClientView();
//        controller = new ClientControllerDelegate();
//        controller.setModel(model);
//        controller.setView(view);
//        view.setModel(model);
//        view.setController(controller);
//    }
//
//    private void run() {
//        controller.run();
//    }
//}
