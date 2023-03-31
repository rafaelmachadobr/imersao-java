import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // Fazer uma conexão HTTP e buscar os top 250 filmes do IMDB
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // Extrair só os dados que interessam(titulo, poster, nota, ano)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // Exibir e manipular os dados
        File diretorio = new File("alura-stickers\\saida");
        diretorio.mkdir();

        GeradoraDeFigurinhas geradora = new GeradoraDeFigurinhas();
        for (Map<String, String> filme : listaDeFilmes) {

            String urlImagem = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = "alura-stickers\\saida\\" + titulo + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println("\u001b[1mTítulo:\u001b[m " + titulo);
            System.out.println("\u001b[1mPoster:\u001b[m " + filme.get("image"));
            System.out.println(filme.get("year"));
            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            System.out.println("\u001b[1m\u001b[44m\u001b[37mClassificação: " + classificacao + "\u001b[m");
            int numeroEstrelinhas = (int) classificacao;
            for (int i = 1; i < numeroEstrelinhas; i++) {
                System.out.print("⭐");
            }
            System.out.println("\n");

        }
    }
}
