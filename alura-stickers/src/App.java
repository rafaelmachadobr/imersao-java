import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // Fazer uma conexão HTTP e buscar os top 250 filmes do IMDB
        // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        // ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        ClienteHttp http = new ClienteHttp();
        String json = http.buscaDados(url);

        // Exibir e manipular os dados
        List<Conteudo> conteudos = extrator.extrairConteudos(json);

        File diretorio = new File("alura-stickers\\saida");
        diretorio.mkdir();

        GeradoraDeFigurinhas geradora = new GeradoraDeFigurinhas();
        for (int i = 0; i < conteudos.size(); i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
            String nomeArquivo = "alura-stickers\\saida\\" + conteudo.titulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            // System.out.println("\u001b[1mTítulo:\u001b[m " + titulo);
            // System.out.println("\u001b[1mPoster:\u001b[m " + conteudo.get("image"));
            // System.out.println(conteudo.get("year"));
            // double classificacao = Double.parseDouble(conteudo.get("imDbRating"));
            // System.out.println("\u001b[1m\u001b[44m\u001b[37mClassificação: " + classificacao + "\u001b[m");
            // int numeroEstrelinhas = (int) classificacao;
            // for (int i = 1; i < numeroEstrelinhas; i++) {
            //     System.out.print("⭐");
            // }
            // System.out.println("\n");

            System.out.println(conteudo.titulo());
            System.out.println();

        }
    }
}
