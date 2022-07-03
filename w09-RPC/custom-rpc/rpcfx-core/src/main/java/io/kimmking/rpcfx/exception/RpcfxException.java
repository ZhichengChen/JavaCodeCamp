package io.kimmking.rpcfx.exception;

public class RpcfxException extends RuntimeException{

  public RpcfxException(Object obj){
    super(obj.toString());
  }
}
