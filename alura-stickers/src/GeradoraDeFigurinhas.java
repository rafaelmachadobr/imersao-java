import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {

    public void cria() throws Exception {

        // Leitura da imagem
        BufferedImage imagemOriginal = ImageIO.read(new File("alura-stickers\\entrada\\filme.jpg"));

        // Cria nova imagem em memória com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // Copiar a imagem original pra nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // Escrever uma frase na nova imagem
        graphics.drawString("TOPZERA", 10, novaAltura - 100);

        // Escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File("alura-stickers\\saida\\figurinha.png"));

    }

    public static void main(String[] args) throws Exception {
        GeradoraDeFigurinhas gerador = new GeradoraDeFigurinhas();
        gerador.cria();
    }

}
