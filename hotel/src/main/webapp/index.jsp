<html>
<body>
    <h2>Agoda</h2>
    
    <p>Hotel Search</p>
    <form action="api/hotel/city" method="GET">
        City <input id="city" name="city"/>
        <br><br>
        Sort by Price
        <input type="radio" name="sort" value="" checked>None
        <input type="radio" name="sort" value="asc" >Ascending
        <input type="radio" name="sort" value="desc">Descending
        <br><br>
        <input type="submit" />
    </form>
</body>

</html>
